$(document).ready(function () {
     $("#search_box").off("keyup").off("keydown").off("click").on("keyup", function () {
          var word = $(this).val();
          if (word != null && word != "") {
               $.ajax({
                    url: "/home/search",
                    method:'POST',
                    data: { word: word },
                    dataType: "html",
                    success: function (list) {
                         $("#listAutoComplete").html(list);
                    }
               });
          }
          else $("#listAutoComplete").html("");
     });
     $("#search_btn").off('click').on('click', function (e) {
          e.preventDefault();
          var box = $('#search_box');
          var type = box.data("type");
          var id = box.data("id");
          //var val = box.val();
          //if (val != "" && type != 1 && type != 2 && type != 3) type = 4;
          var url = "";
          switch (type) {
               case 1:
                    url = "/book/detail?id="+id;
                    break;
               case 2:
                    url = "/book-by/filtered-by-author?select_author="+id;
                    break;
               case 3:
                    url = "/book-by/filtered-by-publisher?select_publisher="+id;
                    break;
               case 4:
                    url = "/book-by/filtered-by-word?keyword=" + val;
                    break;
               default:
                    url = "/book-by/";
                    break;
          }
          window.location.href = url;
     });
});