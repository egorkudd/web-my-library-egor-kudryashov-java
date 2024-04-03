function active_menu() {
    const url = new URL(window.location);
    const genre_id = url.searchParams.get('genre');
    if (genre_id != null) {
        document.querySelectorAll(".dropdown__options a")
            .forEach((el) => {
                if (genre_id === el.getAttribute("id")) {
                    el.classList.add("active");
                }
            });
    }
}

function get_genres() {
    let genres_str = '';
    let genre_list = get_data('../data/genres.json');
    for (let i = 0; i < genre_list.length; i++) {
        genres_str += `<a id=${genre_list[i].id} href="book_list.html?genre=${genre_list[i].id}">${genre_list[i].name}</a>`;
    }

    let genres_nav = document.createElement("nav");
    genres_nav.className = "dropdown__options";
    genres_nav.innerHTML = genres_str;

    let button = document.getElementById("genres-button");
    button.parentNode.insertBefore(genres_nav, genres_nav.nextSibling);
}

function get_contacts() {
    let contactsStr = '';
    const contacts = get_data('../data/contacts.json');
    for (let i = 0; i < contacts.length; i++) {
        contactsStr += `<li id="${contacts[i].cssClass}">${contacts[i].name}</li>`;
    }

    let contacts_list = document.createElement("ul");
    contacts_list.className = "markers";
    contacts_list.innerHTML = contactsStr;

    let footer_head = document.getElementById("footer-head");
    footer_head.parentNode.insertBefore(contacts_list, contacts_list.nextSibling);
}

function get_cards_html(cardsHtml) {
    let cards_list = document.createElement("article");
    cards_list.className = "cards";
    cards_list.innerHTML = cardsHtml;

    let cards_title = document.getElementById("cards-title");
    cards_title.parentNode.insertBefore(cards_list, cards_list.nextSibling);
}

function get_all_cards_html() {
    let cardsHtml = '';
    const book_cards = get_data('../data/cards.json');
    console.log(book_cards)
    for (let i = 0; i < book_cards.length; i++) {
        cardsHtml += get_card_html(book_cards[i]);
    }

    get_cards_html(cardsHtml);
}

function get_card_html(book) {
    return `\
    <article class="card">\
      <section class="card__top">\
        <div class="card__top__image">\
          <img src="${book.imageSource}"/>\
        </div>\
      </section>\
      <div class="card__bottom">\
        <div class="book-info">\
          <section class="book-info__genre">${book.genre}</section>\
          <section class="book-info__author-mark">${book.mark}</section>\
        </div>\
        <a href="book_description.html?id=${book.id}" class="card__bottom__book-name">${book.name}</a>\
      </div>\
    </article>`;
}

function get_book_description() {
    const url = new URL(window.location);
    const id = url.searchParams.get('id');
    const book = get_data(`../data/book_desc/${id}.json`);

    let description = document.getElementById("description-card");
    description.innerHTML = get_book_description_html(book);
}

function get_book_description_html(book) {
    return `\
    <img src="${book.imageSource}">\
    <section class="desc-container">\
      <p class="desc-container__desc-author">${book.author}</p>\
      <p class="desc-container__desc-name">${book.name}</p>\
      <p class="desc-container__desc-text">${book.description}</p>\
    </section>\
    <div class="same-books">\
      <h6 class="same-books__title">Похожая литература по мнению меня</h6>\
      ${get_same_book_list(book)}\
    </div>`;
}

function get_same_book_list(book) {
    let str = `<nav class="same-books__container">`;
    for (let i = 0; i < book.sameBookList.length; i++) {
        str += `<a class="same-books__item" href="book_description.html?id=${book.sameBookList[i].id}">${book.sameBookList[i].name}</a>`;
    }

    str += `</nav>`
    return str;
}

function get_cards_html_by_params() {
    const url = new URL(window.location);

    const name = url.searchParams.get('book_name');
    if (name != null) get_cards_html(get_cards_html_by_name(name));

    const genre = url.searchParams.get('genre');
    console.log(genre)
    if (genre != null) get_cards_html(get_cards_html_by_genre(genre));
}

function get_cards_html_by_name(name) {
    // const book_list = get_data(`../data/cards_by_word/${name}.json`);
    // let cardsHtml = '';
    // for (let i = 0; i < book_list.length; i++) {
    //     cardsHtml += get_card_html(book_list[i]);
    // }

    const book_list = get_data(`../data/cards.json`);
    let cardsHtml = '';
    for (let i = 0; i < book_list.length; i++) {
        if (~book_list[i].name.toLowerCase().indexOf(name)) {
            cardsHtml += get_card_html(book_list[i]);
        }
    }

    return cardsHtml;
}

function get_cards_html_by_genre(genre_id) {
    let cardsHtml = '';
    const card_list = get_data(`../data/cards_by_genre_id/${genre_id}.json`);
    for (let i = 0; i < card_list.length; i++) {
        cardsHtml += get_card_html(card_list[i]);
    }

    return cardsHtml;
}

const startTime = new Date().getTime();
window.addEventListener('load', function () {
    const endTime = new Date().getTime();

    const loadTime = (endTime - startTime) / 1000;
    let load_time = document.createElement("p");
    load_time.className = "time-place";
    load_time.innerHTML = 'Page loade time is ' + loadTime + " seconds";

    let footer_head = document.getElementById("footer-head")
    footer_head.parentNode.insertBefore(load_time, load_time.nextSibling);
});

function get_data(url) {
    const xhr = new XMLHttpRequest();
    xhr.open('GET', url, false);
    xhr.send(null);
    if (xhr.status === 200) {
        return JSON.parse(xhr.responseText);
    } else {
        throw new Error('Request failed: ' + xhr.statusText);
    }
}

