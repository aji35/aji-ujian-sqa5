@Checkout
Feature: Validasi Informasi Pengiriman pada Halaman Checkout dengan PageFactory
  Sebagai pengguna yang telah memilih produk ke keranjang belanja
  Saya ingin melengkapi data diri pada proses checkout
  Agar saya dapat memastikan sistem memvalidasi kolom kode pos (Postal Code) yang wajib diisi

  Background:
    Given Pengguna membuka portal login "https://www.saucedemo.com/"
    When Pengguna mengisi username "standard_user"
    And Pengguna mengisi password "secret_sauce"
    And Pengguna mengklik tombol Login
    Then Pengguna berhasil diarahkan ke halaman katalog dengan judul "Products"
    When Pengguna memilih produk "Sauce Labs Backpack" untuk ditambahkan ke keranjang
    And Pengguna membuka halaman keranjang belanja
    And Pengguna mengklik tombol checkout

  @Regression @Negative
  Scenario: Validasi Pesan Kesalahan saat Kode Pos Dikosongkan
    When Pengguna mengisi nama depan "John"
    And Pengguna mengisi nama belakang "Doe"
    And Pengguna mengosongkan kode pos
    And Pengguna mengklik tombol Continue
    Then Sistem menampilkan pesan kesalahan checkout "Error: Postal Code is required"

  @Regression @Negative
  Scenario Outline: Validasi Pesan Kesalahan saat Kolom Kode Pos Kosong Menggunakan Data Table
    When Pengguna mengisi nama depan "<nama_depan>"
    And Pengguna mengisi nama belakang "<nama_belakang>"
    And Pengguna mengisi kode pos "<kode_pos>"
    And Pengguna mengklik tombol Continue
    Then Sistem menampilkan pesan kesalahan checkout "<pesan_error>"

    Examples:
      | nama_depan | nama_belakang | kode_pos | pesan_error                    |
      | Jane       | Smith         |          | Error: Postal Code is required |
