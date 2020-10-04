const input_bibtex_source = document.querySelector('.bibtex_source').value;
const btn_bibtex_header = document.querySelector('.btn-bibtex-header');

//ヘッダーのbibtex補完ボタンが押された時
btn_bibtex_header.addEventListener('click', inputDialog);

function inputDialog() {
    // 入力ダイアログを表示 ＋ 入力内容を bibtex_source に代入
    bibtex_source = window.prompt("BibTeXのソースを入力してください", "");
    console.log(bibtex_source);
    inputBibtex(bibtex_source);
}

//ボディのbibtex補完ボタンが押された時
document.querySelector('.btn-bibtex').onclick = function() {
    bibtex_source = document.querySelector(".form-bibtex_source").value;
    console.log(bibtex_source);
    inputBibtex(bibtex_source);
};

//他の関数を用いて入力フォームに値を挿入
function inputBibtex(bibtex_source) {

  if (bibtex_source != "cancel" && bibtex_source != "") {
    var bibtex_contents = resoluteBibtex(bibtex_source);
    var bibitem = convertBibitem(bibtex_contents);

    //入力フォームに値を代入
    console.log(bibtex_contents['author']);
    document.querySelector(".form-title").value = bibtex_contents["title"];
    document.querySelector(".form-authors").value = bibtex_contents["author"];
    document.querySelector(".form-bibtex_source").value = bibtex_source;
    document.querySelector(".form-bibitem_source").value = bibitem;

    //出力確認
    console.log(bibtex_contents);
    console.log(bibitem);
  }
}

// BibTeXソースを要素ごとの連想配列に分解
function resoluteBibtex(bibtex_source) {
  var bibtex_source_tailcut = bibtex_source.replace(/}[ \n]*}/, ""); //　末尾の'} }'を削除
  var bibtex_splited = bibtex_source_tailcut.split(/},[ \n]*/); //カンマ+スペースで区切ることで}でリストにする

  var bibtex_contents = {}; //辞書型
  var value, value_splited;

  for (var index in bibtex_splited) {
    value = bibtex_splited[index];

    if (value.charAt(0) == "@") {
      value_splited = value.split("{");
      bibtex_contents["@"] = value_splited[0]; //例）@inproceedings
      bibtex_contents["label"] = value_splited[1].substring(
        //value_splited[1]=kawasaki2019user,
        (title = 0),
        value_splited[1].indexOf(",")
      ); //kawasaki2019user
      var next_content_by_label = value_splited[1].replace(
        /[ \w]*, *[ \n]*/,
        ""
      ); //title=
      //   console.log(next_content_by_label);
      bibtex_contents[next_content_by_label.replace("=", "")] =
        value_splited[2]; //User-Density Dependent Autonomous Clustering for MANET Based on the Laplace Equation
    } else {
      value_splited = value.split("={");
      bibtex_contents[value_splited[0]] = value_splited[1];
    }
  }

  return bibtex_contents;
}

//bibtexソースの連想配列からbibitem（会田先生仕様）を作成
function convertBibitem(bibtex_contents) {
  var bibitem = "";

  if (bibtex_contents["@"] == "@book") {
    // author
    var authors = generateAuthor(bibtex_contents["author"]);
    bibitem += authors + ", ";
    // title
    bibitem += "{\\em " + bibtex_contents["title"] + "}, ";
    // publisher
    bibitem += bibtex_contents["publisher"] + ", ";
    // year
    bibitem += bibtex_contents["year"] + ".";
  } else {
    // author
    var authors = generateAuthor(bibtex_contents["author"]);
    bibitem += authors + ", ";
    // title
    bibitem += "``" + bibtex_contents["title"] + ",'' ";
    // book title
    if (bibtex_contents["booktitle"]) {
      bibitem += "{\\em " + bibtex_contents["booktitle"] + "}, ";
    }
    // journal
    if (bibtex_contents["journal"]) {
      bibitem += "{\\em " + bibtex_contents["journal"] + "}, ";
    }
    // volume
    if (bibtex_contents["volume"]) {
      bibitem += "vol.~" + bibtex_contents["volume"] + ", ";
    }
    // no
    if (bibtex_contents["number"]) {
      bibitem += "no.~" + bibtex_contents["number"] + ", ";
    }
    // pages
    if (bibtex_contents["pages"]) {
      bibitem += "pp.~" + bibtex_contents["pages"] + ", ";
    }
    // year
    if (bibtex_contents["year"]) {
      bibitem += bibtex_contents["year"] + ".";
    }
  }

  return bibitem;//会田先生仕様のbibitem
}

//連想配列のauthors要素を会田先生仕様に変更
function generateAuthor(authors) {
  var authors_string = "";
  var tmp_name, tmp_first_name, tmp_family_name;

  var authors_splited = authors.split(" and ");
  var authors_splited_length = authors_splited.length;
  if (authors_splited_length >= 2) {
    for (var i = 0; i < authors_splited_length - 1; i++) {//著者-1まで
      tmp_name = authors_splited[i].split(/, */);
      tmp_first_name = tmp_name[1];
      tmp_family_name = tmp_name[0];
      authors_string +=
        tmp_first_name.charAt(0) + ".~" + tmp_family_name + ", ";//名前を会田先生仕様にしている．名前は頭文字+.~
    }
    tmp_name = authors_splited[i].split(/, */);//最後の著者に対して
    tmp_first_name = tmp_name[1];
    tmp_family_name = tmp_name[0];
    authors_string =
      authors_string.slice(0, -2) +
      " and " +
      tmp_first_name.charAt(0) +
      ".~" +
      tmp_family_name;
  } else {
    tmp_name = authors_splited[0].split(/, */);
    tmp_first_name = tmp_name[1];
    tmp_family_name = tmp_name[0];
    authors_string += tmp_first_name.charAt(0) + ".~" + tmp_family_name;
  }

  return authors_string;//会田先生仕様の著者の書き方
}
