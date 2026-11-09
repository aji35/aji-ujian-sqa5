@Login
Feature: Autentikasi Pengguna di Portal SauceDemo dengan PageFactory
  Sebagai pengguna terdaftar di portal SauceDemo
  Saya ingin dapat masuk menggunakan kredensial saya
  Agar saya dapat mengakses katalog produk dan melanjutkan transaksi

  Background:
    Given Pengguna membuka portal login "https://www.saucedemo.com/"

  @Smoke @Positive
  Scenario: Login Berhasil dengan Kredensial Valid
    When Pengguna mengisi username "standard_user"
    And Pengguna mengisi password "secret_sauce"
    And Pengguna mengklik tombol Login
    Then Pengguna berhasil diarahkan ke halaman katalog dengan judul "Products"

  @Regression @Negative
  Scenario Outline: Login Gagal dengan Berbagai Variasi Kredensial Tidak Valid
    When Pengguna mengisi username "<username>"
    And Pengguna mengisi password "<password>"
    And Pengguna mengklik tombol Login
    Then Sistem menampilkan pesan kesalahan login "<pesan_error>"

    Examples:
      | username        | password       | pesan_error                                                |
      |                 | secret_sauce   | Epic sadface: Username is required                         |
      | standard_user   |                | Epic sadface: Password is required                         |
      | user_salah      | pass_salah     | Epic sadface: Username and password do not match any user in this service |
      | locked_out_user | secret_sauce   | Epic sadface: Sorry, this user has been locked out.        |

