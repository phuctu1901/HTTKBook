
var login = {
     init: function () {
          login.registerEvents();
     },
     registerEvents: function () {
          $(".btn-login").off("click").on("click", function (e) {
               // Chặn các sự kiện mặc định 
               e.preventDefault();
               // Khai báo biến gửi lên sever
              // var btn = $(this);
               var username = $('input[name="email"]').val();
               var password = $('input[name="password"]').val();
               if (username == '') {
                    alert('Nhap ten nguoi dung');
                    return false;
               }
               else if (password == '') {
                    alert('Nhap mat khau');
                    return false;
               }
               var data1 = $('form#form_login').serialize();

               $.ajax({
                    type: 'POST',
                    url: '/account/login',
                    data: data1,
                    dataType: "json",
                    success: function (data) {
                         $('#login-modal').css("display", "none");
                         $('.modal-backdrop.fade.show').remove();
                         $('body').css('overflow', 'scroll');
                         if (data.check == false) {
                              alert('Khong co tai khoan! Hay dang ky truoc!');
                         }
                         else {
                              $('#hello').text("Welcome " + data.username);
                              $('#logout').css('display', 'inline');
                              $('#login').css('display', "none");
                              $('#userprofile').css('display', 'inline');
                              $('#register').css('display', "none");
                              var cart;
                              $.ajax({
                                   type: 'POST',
                                   url: '/cart/cart',
                                   data: cart,
                                   dataType: 'json',
                                   success: function (cart) {
                                        $('#cart-icon').text(cart.totalquantity);
                                   }
                              });
                              if (window.location.pathname == '/account/Register')
                                   window.location.pathname = '/home/index';
                              if (window.location.pathname == '/cart')
                                   window.location.pathname = '/cart';
                              return false;
                         }
                    }
               });
               return false;
          })
     }
}
// login.init();
