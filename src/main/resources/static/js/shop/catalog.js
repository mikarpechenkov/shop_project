$(function () {
    const token = $("meta[name='_csrf']").attr("content");
    const header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});
$(document).ready(function () {
    const sendingToCartForms = document.getElementsByName('sendingToCartForm');

    sendingToCartForms.forEach(function (form) {
        let wishButton = form.elements['wish'];
        let presenceInCartInput = form.elements['presenceInCart'];
        let presenceInCart = presenceInCartInput.value === 'true';
        const productId = Number(form.elements['productId'].value);

        wishButtonHandler(wishButton, presenceInCart);

        form.addEventListener('submit', function (event) {
            event.preventDefault();
            event.stopPropagation();

            wishButton = form.elements['wish'];
            presenceInCartInput.value = presenceInCartInput.value === 'true' ? 'false' : 'true';
            presenceInCart = presenceInCartInput.value === 'true';
            let productQuantity = presenceInCart === true ? 1 : 0;

            wishButtonHandler(wishButton, presenceInCart);
            sendPurchasedProductData(productId, productQuantity);
        })
    })
})

function wishButtonHandler(button, presenceInCart) {

    if (presenceInCart === true) {
        button.innerHTML = 'Удалить';
        button.classList.remove('btn-primary');
        button.classList.add('btn-danger');
    } else {
        button.innerHTML = 'В корзину';
        button.classList.remove('btn-danger');
        button.classList.add('btn-primary');
    }
}


function cartCounterHandler(quantity) {
    const cartButton = document.getElementById('cart');

    cartButton.text = 'Корзина (' + quantity + ')';
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
        success: function (response) {
            cartCounterHandler(response.quantity);
        },
        error: function (response) {
            console.log('error', response)
        }
    })
}


