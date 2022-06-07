window.onload = function() {
    setInterval(calculateCogs2, 500);
}

function calculateCogs2() {
    let sum1 = 0;
    let sum2 = 0;
    let v1 = 0
    for(let i = 0; i < document.getElementsByClassName("cogsData1").length; i++){
        v1 = parseInt(document.getElementsByClassName("cogsData1")[i].innerText,10) *
            parseInt(document.getElementsByClassName("cogsData2")[i].innerText,10);
        sum1 += v1 + v1*25/100 - v1*3/100*4;
        sum2 += v1 + v1*25/100 - v1*3/100*4  - v1;
    }


    document.getElementById("cogsDataRes").innerText = "COGS: " + sum1.toFixed(2) +"$";
    document.getElementById("cogsDataResP").innerText = "Profit: " + sum2.toFixed(2)+"$";
}