function openNav() {
    document.getElementsByClassName("sidebar")[0].style.width = "15vw";
    document.getElementsByClassName("sidebarBtn")[0].style.display="none";
}

function closeNav() {
    document.getElementsByClassName("sidebar")[0].style.width="0vw";
    document.getElementsByClassName("sidebarBtn")[0].style.display="flex";
}

window.onload = function() {
    setInterval(sliderValue, 100);
    let productList = document.getElementsByClassName("card-title");
    let costList = document.getElementsByClassName("prPrice");
    let selectList = document.getElementById("subject");
    selectList.innerHTML="";
    for(let i = 0; i < productList.length; i++){
        selectList.innerHTML += "<option value='" + costList[i].textContent + "'>" + productList[i].textContent;
    }
}

function sliderValue() {
    let v1 = parseInt(document.getElementById("prQty").value,10);
    document.getElementById("sliderVal1").innerText = "#" + v1;
    let v2 = parseInt(document.getElementById("timeQty").value,10);
    document.getElementById("sliderVal2").innerText = "#" + v2;
    let v3 = parseInt(document.getElementById("endQty").value,10);
    document.getElementById("sliderVal3").innerText = "#" + v3;
}

function calculateCogs() {
    let v1 = parseInt(document.getElementById("prQty").value,10) *
        document.getElementById("subject").value;
    let v2 = parseInt(document.getElementById("timeQty").value,10);
    let v3 = parseInt(document.getElementById("endQty").value,10);

    document.getElementById("result").value = (v1 + v1*v2/100 - v1*3/100*v3);
    document.getElementById("resultP").value = ((v1 + v1*v2/100 - v1*3/100*v3)  - v1).toFixed(2);
}

