// const pageLinks = document.querySelectorAll('.pagination .page-link');
// const previousLink = document.querySelector('.pagination .page-link[aria-label="Previous"]');
// const nextLink = document.querySelector('.pagination .page-link[aria-label="Next"]');
// let currentPage = 1;
//
// // Xử lý sự kiện khi click vào các liên kết số trang
// pageLinks.forEach((link) => {
//     link.addEventListener('click', (event) => {
//         event.preventDefault();
//         const pageNumber = parseInt(link.textContent);
//         if (pageNumber) {
//             currentPage = pageNumber;
//             // Gọi hàm để thực hiện thay đổi trang hiển thị
//             renderPage(currentPage);
//         }
//     });
// });
//
// // Xử lý sự kiện khi click vào liên kết "Previous"
// previousLink.addEventListener('click', (event) => {
//     event.preventDefault();
//     if (currentPage > 1) {
//         currentPage--;
//         // Gọi hàm để thực hiện thay đổi trang hiển thị
//         renderPage(currentPage);
//     }
// });
//
// // Xử lý sự kiện khi click vào liên kết "Next"
// nextLink.addEventListener('click', (event) => {
//     event.preventDefault();
//     currentPage++;
//     // Gọi hàm để thực hiện thay đổi trang hiển thị
//     renderPage(currentPage);
// });
//
// // Hàm để thực hiện thay đổi trang hiển thị
// function renderPage(page) {
//     // Gọi các hàm hoặc thực hiện các thay đổi cần thiết khi chuyển trang
//     console.log('Đã chuyển đến trang:', page);
// }

const productList = document.getElementById('productList');
const prevButton = document.getElementById('prevButton');
const nextButton = document.getElementById('nextButton');
const itemsPerPage = 6; // Số sản phẩm hiển thị trên mỗi trang
let currentPage = 1;

// Ẩn tất cả các sản phẩm
function hideAllProducts() {
    const products = productList.getElementsByClassName('products-single fix');
    for (let i = 0; i < products.length; i++) {
        products[i].style.display = 'none';
    }
}

// Hiển thị các sản phẩm của trang hiện tại
function showCurrentPage() {
    hideAllProducts();
    const startIndex = (currentPage - 1) * itemsPerPage;
    const endIndex = startIndex + itemsPerPage;
    const products = productList.getElementsByClassName('products-single fix');
    for (let i = startIndex; i < endIndex && i < products.length; i++) {
        products[i].style.display = 'block';
    }
}

// Xử lý sự kiện khi click vào nút "Previous"
prevButton.addEventListener('click', () => {
    if (currentPage > 1) {
        currentPage--;
        showCurrentPage();
    }
});

// Xử lý sự kiện khi click vào nút "Next"
nextButton.addEventListener('click', () => {
    const products = productList.getElementsByClassName('products-single fix');
    const totalPages = Math.ceil(products.length / itemsPerPage);
    if (currentPage < totalPages) {
        currentPage++;
        showCurrentPage();
    }
});

// Hiển thị trang đầu tiên khi tải trang
showCurrentPage();

const pageLinks = document.getElementsByClassName('pageitem');
const productList = document.getElementById('productList');
const itemsPerPage = 6; // Số sản phẩm hiển thị trên mỗi trang

// Ẩn tất cả các sản phẩm
function hideAllProducts() {
    const products = productList.getElementsByClassName('products-single');
    for (let i = 0; i < products.length; i++) {
        products[i].style.display = 'none';
    }
}

// Hiển thị các sản phẩm của trang hiện tại
function showCurrentPage(page) {
    hideAllProducts();
    const startIndex = (page - 1) * itemsPerPage;
    const endIndex = startIndex + itemsPerPage;
    const products = productList.getElementsByClassName('products-single');
    for (let i = startIndex; i < endIndex && i < products.length; i++) {
        products[i].style.display = 'block';
    }
}

// Xử lý sự kiện khi click vào các liên kết trong danh sách phân trang
for (let i = 0; i < pageLinks.length; i++) {
    const pageLink = pageLinks[i];
    const page = i + 1; // Số trang tương ứng với liên kết
    pageLink.addEventListener('click', () => {
        showCurrentPage(page);
    });
}

// Hiển thị trang đầu tiên khi tải trang
showCurrentPage(1);

// Dữ liệu sản phẩm
const productList = [
    {
        sale: true,
        imagePath: "../images/img-pro-01.jpg",
        tooltipTitles: ["View", "Compare", "Add to Wishlist"],
        name: "Lorem ipsum dolor sit amet",
        price: "$9.79"
    },
    {
        sale: true,
        imagePath: "../images/img-pro-02.jpg",
        tooltipTitles: ["View", "Compare", "Add to Wishlist"],
        name: "Lorem ipsum dolor sit amet",
        price: "$9.79"
    },
    {
        sale: true,
        imagePath: "../images/img-pro-03.jpg",
        tooltipTitles: ["View", "Compare", "Add to Wishlist"],
        name: "Lorem ipsum dolor sit amet",
        price: "$9.79"
    },
    {
        sale: true,
        imagePath: "../images/img-pro-01.jpg",
        tooltipTitles: ["View", "Compare", "Add to Wishlist"],
        name: "Lorem ipsum dolor sit amet",
        price: "$9.79"
    },    {
        sale: true,
        imagePath: "../images/img-pro-01.jpg",
        tooltipTitles: ["View", "Compare", "Add to Wishlist"],
        name: "Lorem ipsum dolor sit amet",
        price: "$9.79"
    },


    // ... các sản phẩm khác ...
];

// Số sản phẩm trên mỗi trang
const productsPerPage = 3;

// Tính toán số trang dựa trên số sản phẩm và sản phẩm trên mỗi trang
const pageCount = Math.ceil(productList.length / productsPerPage);

// Hiển thị sản phẩm trên trang hiện tại
function displayProducts(page) {
    // Tính chỉ số bắt đầu và chỉ số kết thúc của sản phẩm trên trang
    const startIndex = (page - 1) * productsPerPage;
    const endIndex = startIndex + productsPerPage;

    // Lấy danh sách sản phẩm trên trang hiện tại
    const currentPageProducts = productList.slice(startIndex, endIndex);

    // Chuỗi HTML cho sản phẩm trên trang hiện tại
    let productListHTML = '';

    currentPageProducts.forEach(product => {
        productListHTML += `
      <div class="col-sm-6 col-md-6 col-lg-4 col-xl-4">
        <!-- ... mã HTML cho sản phẩm ... -->
            <div class="products-single fix">
                <div class="box-img-hover">
                    <div class="type-lb">
                        <p class="new">New</p>
                    </div>
                    <img src="../images/img-pro-02.jpg" class="img-fluid" alt="Image">
                    <div class="mask-icon">
                        <ul>
                            <li><a href="#" data-toggle="tooltip" data-placement="right" title="View"><i class="fas fa-eye"></i></a></li>
                            <li><a href="#" data-toggle="tooltip" data-placement="right" title="Compare"><i class="fas fa-sync-alt"></i></a></li>
                            <li><a href="#" data-toggle="tooltip" data-placement="right" title="Add to Wishlist"><i class="far fa-heart"></i></a></li>
                        </ul>
                        <a class="cart" href="#">Add to Cart</a>
                    </div>
                </div>
                <div class="why-text">
                    <h4>Lorem ipsum dolor sit amet</h4>
                    <h5> $9.79</h5>
                </div>
            </div>
      </div>
    `;
    });

    // Thêm nội dung sản phẩm vào phần tử đã tồn tại trong tài liệu HTML
    $("#grid-view").html(productListHTML);
}

// Hiển thị phân trang
function displayPagination() {
    const paginationElement = $("#pagination");

    // Chuỗi HTML cho các nút phân trang
    let paginationHTML = '';

    for (let i = 1; i <= pageCount; i++) {
        paginationHTML += `
      <li class="page-item"><a class="page-link" href="#" data-page="${i}">${i}</a></li>
    `;
    }

    // Thêm nội dung phân trang vào phần tử đã tồn tại trong tài liệu HTML
    paginationElement.html(paginationHTML);

    // Xử lý sự kiện khi người dùng nhấp vào nút phân trang
    paginationElement.on("click", "a.page-link", function(e) {
        e.preventDefault();

        const page = parseInt($(this).data("page"));

        // Hiển thị sản phẩm trên trang được chọn
        displayProducts(page);
    });
}

// Hiển thị sản phẩm trên trang đầu tiên khi tải xong trang
displayProducts(1);

// Hiển thị phân trang khi tải xong trang
displayPagination();