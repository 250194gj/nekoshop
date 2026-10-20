<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>NekoShop | ファッションストア</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
          rel="stylesheet">

    <style>
        body {
            background-color: #f8f8f8;
            color: #222;
        }

        .navbar-brand {
            font-weight: 800;
            letter-spacing: 1px;
        }

        .hero {
            background: #eae5df;
            padding: 48px 24px;
            border-radius: 12px;
        }

        .product-card {
            height: 100%;
            border: none;
            border-radius: 12px;
            overflow: hidden;
            transition: transform 0.2s ease,
            box-shadow 0.2s ease;
        }

        .product-card:hover {
            transform: translateY(-4px);
            box-shadow: 0 8px 24px rgba(0, 0, 0, 0.10);
        }

        .product-image {
            width: 100%;
            height: 260px;
            object-fit: contain;
            background-color: #fff;
            padding: 12px;
        }

        .product-name {
            font-size: 1rem;
            font-weight: 600;
        }

        .product-price {
            font-size: 1.1rem;
            font-weight: 700;
            color: #b42318;
        }

        .product-description {
            color: #777;
            font-size: 0.875rem;
        }

        @media (max-width: 576px) {
            .product-image {
                height: 180px;
            }

            .hero {
                padding: 28px 18px;
            }
        }
    </style>
</head>

<body>

<!-- Thanh điều hướng -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand"
           href="${pageContext.request.contextPath}/">
            NekoShop
        </a>

        <button class="navbar-toggler"
                type="button"
                data-bs-toggle="collapse"
                data-bs-target="#mainNavbar"
                aria-controls="mainNavbar"
                aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="mainNavbar">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link active"
                       href="${pageContext.request.contextPath}/">
                        商品一覧
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<main class="container py-4 py-md-5">

    <!-- Banner giới thiệu -->
    <section class="hero mb-5">
        <p class="text-uppercase text-secondary mb-2">
            Everyday Fashion
        </p>

        <h1 class="fw-bold">お気に入りを見つけよう。</h1>

        <p class="mb-0 text-secondary">
            毎日のコーディネートをもっと楽しく。
        </p>
    </section>

    <!-- Tiêu đề danh sách -->
    <div class="d-flex justify-content-between
                align-items-center mb-4">
        <div>
            <h2 class="fw-bold mb-1">商品一覧</h2>
            <p class="text-secondary mb-0">
                お気に入りのアイテムを見つけましょう。
            </p>
        </div>

        <span class="badge text-bg-dark">
            <c:out value="${products.size()}" /> items
        </span>
    </div>

    <!-- Danh sách sản phẩm -->
    <div class="row g-3 g-md-4">

        <c:forEach var="product" items="${products}">
            <div class="col-6 col-md-4 col-lg-3">

                <article class="card product-card shadow-sm">

                    <img
                            src="${pageContext.request.contextPath}${product.image}"
                            alt="${product.name}"
                            class="product-image"
                            loading="lazy">

                    <div class="card-body d-flex flex-column">

                        <h3 class="product-name mb-2">
                            <c:out value="${product.name}" />
                        </h3>

                        <p class="product-description mb-3">
                            <c:out value="${product.description}" />
                        </p>

                        <p class="product-price mb-3">
                            ¥<c:out value="${product.price}" />
                        </p>

                        <a href="#"
                           class="btn btn-outline-dark mt-auto">
                            詳細を見る
                        </a>

                    </div>
                </article>

            </div>
        </c:forEach>

    </div>

    <!-- Trường hợp chưa có sản phẩm -->
    <c:if test="${empty products}">
        <div class="text-center py-5">
            <h3>商品がありません。</h3>
            <p class="text-secondary">
                現在、表示できる商品はありません。
            </p>
        </div>
    </c:if>

</main>

<footer class="bg-dark text-white text-center py-4 mt-5">
    <small>&copy; NekoShop — Fashion for everyday.</small>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>