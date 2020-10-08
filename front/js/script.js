let rangeSlider = document.getElementById("slider");
let rangeSliderValue = document.getElementById("range-slider-value");
rangeSlider.addEventListener("change", () => {
  rangeSliderValue.innerHTML = rangeSlider.value;
});
