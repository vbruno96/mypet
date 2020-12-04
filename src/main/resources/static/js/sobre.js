function getLoggedUser() {
    const jwtKey = localStorage.getItem("jwtKey");
  
    if (!jwtKey) {
        window.location.replace("/index.html");
    }
  
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "/usuarios", true);
    xhr.setRequestHeader('Authorization', jwtKey);
    xhr.send();
  
    xhr.onreadystatechange = function () {
      if (this.readyState != 4) return;
  
      if (this.status == 200) {
        var data = JSON.parse(this.responseText);     
        loggedUser = data;
        document.getElementById("entrarButton").style.display = "none";
        document.getElementById("perfilButton").style.display = "block";
        document.getElementById("logoutButton").style.display = "block";
      }
      else if (this.status == 403) {
        window.location.replace("/index.html");
      }
  
    };
  }

window.onload = getLoggedUser;