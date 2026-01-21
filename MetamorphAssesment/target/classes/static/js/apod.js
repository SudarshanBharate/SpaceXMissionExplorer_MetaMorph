const BASE_URL = "http://localhost:8080/api/apod";

document.addEventListener("DOMContentLoaded", () => {
    fetchTodayApod();
});

function showLoader(show) {
    document.getElementById("loader").classList.toggle("d-none", !show);
}

function fetchTodayApod() {
    showLoader(true);

    fetch(`${BASE_URL}/today`)
        .then(res => res.json())
        .then(data => {
            renderApod(data);
            showLoader(false);
        })
        .catch(() => showLoader(false));
}

function fetchByDate() {
    const date = document.getElementById("datePicker").value;
    if (!date) {
        alert("Please select a date");
        return;
    }

    showLoader(true);

    fetch(`${BASE_URL}?date=${date}`)
        .then(res => res.json())
        .then(data => {
            renderApod(data);
            showLoader(false);
        })
        .catch(() => showLoader(false));
}

function renderApod(data) {

    const card = document.getElementById("apodCard");
    const image = document.getElementById("apodImage");
    const video = document.getElementById("apodVideo");

    card.classList.remove("d-none");

    document.getElementById("apodTitle").innerText = data.title;
    document.getElementById("apodDate").innerText = data.date;
    document.getElementById("apodExplanation").innerText = data.explanation;

    if (data.mediaType === "image") {
        image.src = data.imageUrl || data.url;
        image.style.display = "block";
        video.classList.add("d-none");
    }
    else if (data.mediaType === "video") {
        video.src = data.imageUrl;
        video.classList.remove("d-none");
        image.style.display = "none";
    }
}

