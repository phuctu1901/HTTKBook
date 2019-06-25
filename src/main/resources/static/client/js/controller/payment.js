var payment = {
     // init: function () {
     //      payment.responseEvents();
     // },
     // responseEvents: function () {
     //      $('#btn-payment').off('click').on('click', function (e) {
     //           e.preventDefault();
     //           var form = $("#form_cart").serialize();
     //           $.ajax({
     //                url: '/order/OrderDetail',
     //                type: 'POST',
     //                data: form,
     //                dataType: 'json',
     //                success: function (res) {
     //                     if (res.check == 0) {
     //                          show_modal('login-modal');
     //                          alert(res.message);
     //                     }
     //                     else if (res.check == 2) {
     //                          alert(res.message);
     //                          window.location.pathname = res.url;
     //                     }
     //                     else if (res.check == 1) {
     //                          window.location.pathname = res.url;
     //                     }
     //
     //                }
     //           })
     //           return false;
     //      });
     //      return false;
     // }
}
$(document).ready(function () {
     $('#cart').DataTable({
          "bSort": false,
          "language": {
               "lengthMenu": "Hiển thị _MENU_ sản phẩm mỗi trang",
               "search": "Tìm kiếm:",
               "paginate": {
                    "previous": "Trước",
                    "next": "Sau"
               }
          },
          "aLengthMenu": [[5, 10, 100, -1], [5, 10, 100, "All"]],
          "iDisplayLength": 5,
          "fnInfoCallback": function (oSettings, iStart, iEnd, iMax, iTotal, sPre) {
               return iStart + " to " + iEnd;
          }
     });
});