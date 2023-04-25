$(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});
$(document).ready(function () {

    const sendingToCartForms = document.getElementsByName('sendingToCartForm');

    sendingToCartForms.forEach(function (form) {
        form.addEventListener('submit', function (event) {
            event.preventDefault();
            event.stopPropagation();

            const wishButton = form.elements['wish'];
            const productId = Number(form.elements['productId'].value);
            let presenceInCartInput = form.elements['presenceInCart'];
            let presenceInCart = presenceInCartInput.value === 'true';
            presenceInCartInput.value = presenceInCartInput.value === 'true' ? 'false' : 'true';
            let productQuantity = presenceInCart === true ? 0 : 1;

            changeWishButton(wishButton, presenceInCart);
            sendPurchasedProductData(productId, productQuantity);

        })
    })
})

function changeWishButton(button, presenceInCart) {

    if (presenceInCart === false) {
        button.innerHTML = "Удалить";
        button.classList.remove('btn-primary');
        button.classList.add('btn-danger');
    } else {
        button.innerHTML = "В корзину";
        button.classList.remove('btn-danger');
        button.classList.add('btn-primary');
    }
}

//Дописать логику
function updateCartCounter(quantity) {
    console.log(quantity);
}

function sendPurchasedProductData(productId, quantity) {

    const jsonData = JSON.stringify({
        productId: productId,
        quantity: quantity
    });

    $.ajax({
        url: '/shop/refreshing_cart',
        cache: 'false',
        type: 'POST',
        dataType: 'json',
        data: jsonData,
        contentType: 'application/json; charset=utf-8',
        success: function(response){
            console.log(response);
        },
        error: function (response) {
            console.log('error', response)
        }
    })
}


