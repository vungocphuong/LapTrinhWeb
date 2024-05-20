/**
 * 
 */
const form = document.querySelector("#otp-form");
const inputs = document.querySelectorAll(".otp-input");
const verifyBtn = document.querySelector("#verify-btn");

const isAllInputFilled = () => {  // kiem tra xem tat ca cac input da duoc dien hay chua
  return Array.from(inputs).every((item) => item.value );
};

const getOtpText = () => {  // lay chuoi otp nhap bang cach ghep cac o input
  let text = "";
  inputs.forEach((input) => {
    text += input.value;
  });
  return text;
};

// const verifyOTP = () => {
//   if (isAllInputFilled()) {
//     const text = getOtpText();
//     alert(`Your OTP is "${text}" .OTP is Correct `);
//   }
// };

const toggleFilledClass = (field) => { //Thêm hoặc xóa class "filled" cho một input dựa trên việc nó có giá trị hay không.
  if (field.value) { 
    field.classList.add("filled");
  } else {
    field.classList.remove("filled");
  }
};

form.addEventListener("input", (e) => {
  const target = e.target;
  const value = target.value;
  console.log({ target, value });
  toggleFilledClass(target);
  if (target.nextElementSibling) {
    target.nextElementSibling.focus();
  }
  
});

inputs.forEach((input, currentIndex) => { // xử lý hành động dán nội dung (paste) vào các ô input trong form OTP
  // fill check toggleFilledClass);

  // paste event
  input.addEventListener("paste", (e) => {
    e.preventDefault();
    const text = e.clipboardData.getData("text");
    console.log();
    inputs.forEach((item, index) => {
      if (index >= currentIndex && text[index - currentIndex]) {
        item.focus();
        item.value = text[index - currentIndex] || "";
        toggleFilledClass(item);

      }
    });
  });

  // backspace event
  input.addEventListener("keydown", (e) => {
    if (e.keyCode === 8) {
      e.preventDefault();
      input.value = "";
      // console.log(input.value);
      toggleFilledClass(input);
      if (input.previousElementSibling) {
        input.previousElementSibling.focus();
      }
    } else {
      if (input.value && input.nextElementSibling) {
        input.nextElementSibling.focus();
      }
    }
  });
});

// verifyBtn.addEventListener("click", () => {
//   verifyOTP();
// });