@Cart
Feature: Penambahan Produk ke Keranjang Belanja dengan PageFactory
  Sebagai pengguna yang telah berhasil masuk ke portal SauceDemo
  Saya ingin memilih dan menambahkan produk ke keranjang belanja
  Agar saya dapat memeriksa item sebelum proses pembayaran

  Background:
    Given Pengguna membuka portal login "https://www.saucedemo.com/"
    When Pengguna mengisi username "standard_user"
    And Pengguna mengisi password "secret_sauce"
    And Pengguna mengklik tombol Login
    Then Pengguna berhasil diarahkan ke halaman katalog dengan judul "Products"

  @Smoke @Positive
  Scenario: Menambahkan Satu Produk Standar ke Keranjang Belanja
    When Pengguna memilih produk "Sauce Labs Backpack" untuk ditambahkan ke keranjang
    Then Ikon keranjang belanja menampilkan angka "1"
    When Pengguna membuka halaman keranjang belanja
    Then Produk "Sauce Labs Backpack" terdaftar di dalam daftar keranjang belanja

  @Regression
  Scenario Outline: Menambahkan Berbagai Pilihan Produk ke Keranjang Belanja
    When Pengguna memilih produk "<nama_produk>" untuk ditambahkan ke keranjang
    Then Ikon keranjang belanja menampilkan angka "1"
    When Pengguna membuka halaman keranjang belanja
    Then Produk "<nama_produk>" terdaftar di dalam daftar keranjang belanja

    Examples:
      | nama_produk               |
      | Sauce Labs Bike Light     |
      | Sauce Labs Bolt T-Shirt   |
      | Sauce Labs Fleece Jacket  |

