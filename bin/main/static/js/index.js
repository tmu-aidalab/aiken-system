const search_button = document.querySelector('.search_button');

//検索ボタンが押された時
search_button.addEventListener('click', makeUrl);

function makeUrl() {
    const text = document.getElementById("search_word").value;
    const current = document.getElementById("search_paper");
    const sortMethodIndex = document.getElementById("sort").selectedIndex;
    const sortKeyIndex = document.getElementById("key").selectedIndex;
    let sortMethod;
    let keyMethod;

    // 何を基準に検索結果をソートするか
    if (sortKeyIndex == 0) {
        keyMethod = "regist";
    } else if (sortKeyIndex == 1) {
        keyMethod = "view";
    } else if (sortKeyIndex == 2) {
        keyMethod = "update";
    } else {
        keyMethod = "freq";
    }

    // 検索結果を降順か昇順か決める
    if (sortMethodIndex == 0) {
        sortMethod = "DESC";
        // console.log(sortMethod);
    } else {
        sortMethod = "ASC";
        // console.log(sortMethod);
    }

    // /search/view?search_key=title&keyword=(textbox)&sorting=DESC
    location.href =
        "/search/" +
        keyMethod +
        "?search_key=title&keyword=" +
        text +
        "&sorting=" +
        sortMethod;
}