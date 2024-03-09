// contohOutput
// alert(1 + 1);
// console.log(1 + 1);
// prompt(1 + 1);

// if (1 + 1 === 2) {
//   console.log("perhitungan masih betul");
// }

// const iniHari = "selasa";

// if (iniHari === "senin") {
//   console.log("Selanat pergi kampus");
// } else if (iniHari === "sabtu") {
//   console.log("liburrr");
// } else {
//   console.log("Biasa saja");
// }

// const iniHari = prompt("isi hari").toLowerCase();

// if (iniHari === "senin") {
//   console.log("Selanat pergi kampus");
// } else if (iniHari === "sabtu") {
//   console.log("liburrr");
// } else {
//   console.log("Biasa saja");
// }

// const nilai = "80";

// if (nilai < 50) {
//   console.log("E");
// } else if (nilai < 60) {
//   console.log("D");
// } else if (nilai < 70) {
//   console.log("C");
// } else if (nilai < 80) {
//   console.log("B");
// } else if (nilai < 100) {
//   console.log("A");
// }

// const password = prompt("buat password");

// // password harus 6 karakter
// if (password.length >= 6) {
//   if (password.indexOf(" ") === -1) {
//     console.log("password valid");
//   } else {
//     console.log("password tidak boleh ada spasi");
//   }
// } else {
//   console.log("password minimal 6 karakter");
// }
// const password = prompt("buat password");

// // password harus 6 karakter
// if (password.length >= 6 && password.indexOf(" ") === -1) {
//   console.log("password valid");
// } else {
//   console.log("tidak valid");
// }

// let pengguna = prompt("login");

// if (pengguna === "admin" || pengguna === "fadhil") {
//   console.log("Boleh masuk");
// } else {
//   console.log("tidak boleh masuk");
// }
// let pengguna = prompt("login");

// if (pengguna !== "admin") {
//   console.log("tidak boleh masuk");
// } else {
//   console.log("Boleh masuk");
// }

// const day = prompt("hari");
// const angka = parseInt(day);

// switch (angka) {
//   case 1:
//     console.log("Sunday");

//     break;

//   case 2:
//     console.log("Monday");

//     break;

//   case 3:
//     console.log("Tuesday");

//     break;

//   case 4:
//     console.log("Wednesday");

//     break;

//   case 5:
//     console.log("Thursday");

//     break;

//   case 6:
//     console.log("Friday");

//     break;

//   case 7:
//     console.log("Saturday");

//     break;

//   default:
//     console.log("Invalid day");
// }

// console.log(1);
// console.log(2);
// console.log(3);
// console.log(4);
// console.log(5);
// console.log(6);
// console.log(7);
// console.log(8);
// console.log(9);
// console.log(10);

// for (let i = 1; i <= 10; i++) {
//   console.log("saya cinta kamu");
//   console.log(i);
// }

// menapilkan angka ganjil kalau genap value i ganti 0
// for (let i = 1; i <= 10; i += 2) {
//   console.log(i);
// }

//hitung mundur
// for (let i = 20; i >= 0; i--) {
//   console.log(i);
// }

// for (let i = 20; i >= 0; i -= 2) {
//   console.log(i);
// }

//perkalian
// for (let i = 10; i <= 10000; i *= 10) {
//   console.log(i);
// }

// setiap melakukan perulangan dengan array, selalu awal wali nilai awal dengan dan batasnya sampai panjangnya suatu data array yg akan digunakan

// const animals = ["cat", "deer", "hog", "koala", "monyet"];

// for (let i = 0; i < animals.length; i++) {
//   console.log(i, animals[i]);
// }

// const animals = ["cat", "deer", "hog", "koala", "monyet"];

// for (let i = 0; i < animals.length; i++) {
//   console.log(i + 1, animals[i]);
// }

//nested loop
// let str = "LOL";
// for (let i = 0; i <= 4; i++) {
//   console.log("Outher", i);
//   for (let j = 0; j < str.length; j++) {
//     console.log("   Inner", str[j]);
//   }
// }

// Perulangan Menggunakan Perintah While

// let num = 0;
// while (num < 10) {
//   console.log(num);
//   num++;
// }

// for (let num = 0; num < 10; num++) {
//   console.log(num);
// }

//const password = "Pass123";

// let guess = prompt("enter the password");
// while (guess !== password) {
//   guess = prompt("enter the password");
// }
// alert("Selamat Datang");

// for (let guess = prompt("enter the password"); guess !== password; ) {
//   guess = prompt("enter the password");
// }

// let input = prompt("hei");

// while (true) {
//   input = prompt(input);
//   if (input.toLowerCase() === "stop") break;
// }
// alert("oke");

// for (i = 0; i < 1000; i++) {
//   console.log(i);
//   if (i === 100) break;
// }

// Cara Elegan Melakukan Perulangan Pada Array For Of

//onst buahan = ["pisang", "jeruk", "apel", "mangga"];

// for (let i = 0; i < buah.length; i++) {
//   console.log(`buah ${buah[i]}`);
// }

// for (let buah of buahan) {
//   console.log(`buah ${buah}`);
// }

// Melakukan Perulangan Pada Object Dengan Elegan For In

// const siswaskor = {
//   Fadhil: 10,
//   Hani: 20,
// };

// for (let siswa in siswaskor) {
//   console.log(`${siswa} memiliki skor ${siswaskor[siswa]}`);
// }

// let total = 0;
// let scores = Object.values(siswaskor);
// for (let score of scores) {
//   total += score;
// }
// console.log(total / scores.length);

// let lemparDadu = Math.floor(Math.random() * 6) + 1;
// console.log(lemparDadu);

// function LemparDadu() {
//   console.log(Math.floor(Math.random() * 6) + 1);
// }

// function nyanyi() {
//   console.log("Do Re Mi");
// }

// function selamatPagi(name) {
//   console.log(`Halo, ${name}. Selamat Pagi`);
// }

// selamatPagi("Fadhil");

// function jumlahkan(a, b) {
//   const total = a + b;
//   console.log(total);
// }

// jumlahkan(1, 2);

//Visibilitas Variabel Sesuai Scope Atau Ruang Lingkup

// let linux = "RedHat";

// function easyLinux() {
//   let linux = "Debian";
//   console.log(linux); // Debian
// }
// easyLinux();

// console.log(linux); // RedHat

// Blocked Scope Variabel
// let tinggi = 8;
// if (tinggi > 5) {
//   let lebar = 10;
//   console.log(lebar);
// }

// console.log(tinggi);

// Lexical Scope
// function funcLuar() {
//   const nilailuar = "Ini string nilai luar";

//   function funcDalam() {
//     let nilaiDalam = `di dalam mengambil ${nilailuar}'`;
//     console.log(nilaiDalam);
//   }

//   funcDalam();
// }
