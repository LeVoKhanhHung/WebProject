document.querySelector('.btn-add-to-cart').addEventListener('click', function () {

    let productName = document.querySelector('#sp').innerText.trim();
    let price = '';
    if (document.querySelector('#btnradio1').checked) {
        price = document.querySelector('#collapseExample1 .fw-bold').innerText.trim();
    } else if (document.querySelector('#btnradio2').checked) {
        price = document.querySelector('#collapseExample2 .fw-bold').innerText.trim();
    } else if (document.querySelector('#btnradio3').checked) {
        price = document.querySelector('#collapseExample3 .fw-bold').innerText.trim();
    }
    let id = document.querySelector('#masanpham').innerHTML.trim();

    let quantity = document.querySelector('.btn-group a').innerText.trim();


    let product = {
        name: productName,
        id:id,
        price: price,
        quantity: parseInt(quantity),
    };


    let cart = JSON.parse(localStorage.getItem('cart')) || [];

    cart.push(product);


    localStorage.setItem('cart', JSON.stringify(cart));


    alert('Sản phẩm đã được thêm vào giỏ hàng!');
});