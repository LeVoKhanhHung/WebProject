let cart = JSON.parse(localStorage.getItem('cart')) || [];
let lastProduct = cart[cart.length - 1]; 


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
    const unitPrice = product.price;
    const count = parseInt(countElement.innerText);
    return unitPrice * product.quantity;
}
updateCartButton.addEventListener('click', () => {
    const totalPrice = calculateTotalPrice();
    document.querySelector('.tamtinhsanpham .price').innerText = `${totalPrice.toLocaleString()}đ`;
    document.querySelector('.rowright .p').innerText = `${totalPrice.toLocaleString()}đ`;
     document.getElementById("tongtienthanhtoan").innerText = `${totalPrice.toLocaleString()}đ`;
});






function updateCart() {
    
    if (cart.length === 0) {
        alert('Giỏ hàng của bạn đang trống!');
        return;
    }

    cart.forEach((product, index) => {
        
        let productElement = document.querySelectorAll('.productdetails')[index]; 

        if (productElement) {
           
            productElement.querySelector('.thongtinsanpham p').innerText = lastProduct.name;

           
            productElement.querySelector('.masanpham .psanpham').innerText = lastProduct.id;

         
            productElement.querySelector('.giasanpham .psanpham').innerText = lastProduct.price;

       
            productElement.querySelector('.countsanpham .sum').innerText = lastProduct.quantity;

            
            var total = lastProduct.price * lastProduct.quantity;
            productElement.querySelector('.tamtinhsanpham .price').innerText = parse(total)+ 'đ';
        }
    });
}


document.addEventListener('DOMContentLoaded', function () {
    updateCart();
});

function giaohang() {
    const giaohangElement = document.getElementById('giaohang');
  
  
    if (giaohangElement.style.display === 'none' || giaohangElement.style.display === '') {
     
      giaohangElement.style.display = 'block';
    } else {
    
      giaohangElement.style.display = 'none';
    }
  }
  
  function calculateShipping() {
   
    const thanhPho = document.getElementById('thanhPho').value;
    const quan = document.getElementById('quan').value;

  
    const shippingRates = {
        "Hà Nội": {
            "Quận Ba Đình": "30.000",   
            "Quận Long Biên": "35.000",  
            "Quận Hoàn Kiếm": "25.000",  
        },
        "Tp.Hồ Chí Minh": {
            "Quận 1": "40.000",
            "Quận 2": "45.000",
        },
        "Tp.Cần Thơ": {
            "Quận Ninh Kiều": "50.000",
        },
        "Tp.Hải Phòng": {
            "Quận Hồng Bàng": "60.000",
        },
        "Tp.Vĩnh Long": {
            "Quận Vĩnh Long": "55.000",
        },
        "Bình Định": {
            "Quận Quy Nhơn": "65.000",
        }
    };


    if (thanhPho && quan) {     
        const fee = shippingRates[thanhPho] && shippingRates[thanhPho][quan];
        
        if (fee !== undefined) {
            document.getElementById('phigiaohang').innerHTML = `Phí giao hàng: ${fee} VND`;
        } else {
            document.getElementById('phigiaohang').innerHTML = 'Phí giao hàng không có sẵn cho khu vực này.';
        }
    } else {
        document.getElementById('phigiaohang').innerHTML = 'Vui lòng chọn thành phố và quận để tính phí.';
    }
}

