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