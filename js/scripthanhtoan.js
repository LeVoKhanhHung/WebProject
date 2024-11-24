let cart = JSON.parse(localStorage.getItem('cart')) || [];
let lastProduct = cart[cart.length - 1]; 
document.getElementById('phigiaohang').innerHTML ='sfsdfsdf';
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
var displaygiaohang = document.getElementById("giaohang");
const shippingFee = localStorage.getItem('shippingFee');
document.getElementById('phigiaohang').innerHTML =`Phí giao hàng: ${shippingFee} VND`;
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
function toggleGuide() {
    var guide = document.getElementById("guide");
    guide.classList.toggle("hidden");
  }


  document.getElementById('tensp').innerHTML = lastProduct.name;
  document.getElementById('masp').innerHTML = lastProduct.id;
  document.getElementById('countspthanhtoan').innerHTML = lastProduct.quantity;
  document.getElementById('price').innerHTML = lastProduct.price;
  document.getElementById('tamtinhprice').innerHTML = lastProduct.price;
  var total = lastProduct.price * lastProduct.quantity;
  document.getElementById('totalsp1').innerHTML =  ` ${parse(total)} + 'd'`;
