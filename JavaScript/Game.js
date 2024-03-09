let maximum = parseInt(prompt("Masukkan Angka Maksimal"));
while (!maximum) {
  maximum = parseInt(prompt("Masukkan Angka"));
}

const targetNum = Math.floor(Math.random() * maximum) + 1;
console.log(targetNum);

let guess = parseInt(prompt("Tebakan kamu"));
let coba = 1;

while (parseInt(guess) !== targetNum) {
  coba++;
  if (guess > targetNum) {
    guess = parseInt(prompt("terlalu tinggi"));
  } else {
    guess = parseInt(prompt("Terlalu Rendah"));
  }
}

alert(`Selamat jawaban anda betul dengan percobaan ${coba} kali`);
