const subButtonthanhtoan = document.getElementById("subspthanhtoan");
const addButtonthanhtoan = document.getElementById("addspthanhtoan");
const countSPthanhtoan = document.getElementById("countspthanhtoan");
function updateCount(value){
let countProduct = parseInt(countSPthanhtoan.innerHTML);
countProduct += value;
if(countProduct < 1)  countProduct = 1;
countSPthanhtoan.innerHTML = countProduct;

}
subButtonthanhtoan.addEventListener('click', () => updateCount(-1));
addButtonthanhtoan.addEventListener('click', () => updateCount(1));
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

var displaygiaohang = document.getElementById("giaohang");
function giaohang(){
    displaygiaohang.style.display = "block";
}
function toggleBox() {
    const box = document.getElementById("newBox");
    if (box.classList.contains("active")) {
        box.classList.remove("active");
       
        box.style.height = box.scrollHeight + 'px';
        setTimeout(() => (box.style.height = '0'), 0);
    } else {
        box.classList.add("active");
        
        box.style.height = box.scrollHeight + 'px';
        setTimeout(() => (box.style.height = 'auto'), 300); 
    }
}
