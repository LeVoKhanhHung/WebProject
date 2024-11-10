
const countElement = document.getElementById('count'); 
const subtractButton = document.getElementById('substract');
const addButton = document.querySelector('.add .icon'); 
const updateCartButton = document.getElementById('updateCart'); 
function updateCount(value) {
    let currentCount = parseInt(countElement.innerText);
    currentCount += value;
    if (currentCount < 1) currentCount = 1;
    countElement.innerText = currentCount;
}
subtractButton.addEventListener('click', () => updateCount(-1));
addButton.addEventListener('click', () => updateCount(1));
function calculateTotalPrice() {
    const unitPrice = 45000;
    const count = parseInt(countElement.innerText);
    return unitPrice * count;
}
updateCartButton.addEventListener('click', () => {
    const totalPrice = calculateTotalPrice();
    document.querySelector('.tamtinhsanpham .price').innerText = `${totalPrice.toLocaleString()}đ`;
    document.querySelector('.rowright .p').innerText = `${totalPrice.toLocaleString()}đ`;
     document.getElementById("tongtienthanhtoan").innerText = `${totalPrice.toLocaleString()}đ`;
});



