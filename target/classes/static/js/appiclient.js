var appiclient=(function(){

    function getCountries(){
        axios({
            method:'get',
            url: "/coronavirus",

        })
        .then(response => app.createTable(response.data))
        .catch(error => console.log(error));
    }

    function getCountryByName(name){
        document.getElementById("pais").style.display="block";
        document.getElementById("row").style.display="block";
        document.getElementById("Table").style.display="table";
        document.getElementById("TableInfo").style.display="table";
        document.getElementById('pais').innerHTML = name;
        getCountryInfo(name);
        axios({
            method:'get',
            url: "/coronavirus/"+ name,

        })
        .then(response => app.createTableByName(response.data.data.covid19Stats))
        .catch(error => console.log(error));
    }

    function getCountryInfo(name){
        axios({
            method:'get',
            url: "/coronavirus/1/"+ name,

        })
        .then(response => app.createTableInfo(response.data))
        .catch(error => console.log(error));
    }

    function getPos(name){
        axios({
            method:'get',
            url: " https://restcountries-v1.p.rapidapi.com/name/"+ name,
            headers:{
                "x-rapidapi-host": "restcountries-v1.p.rapidapi.com",
                "x-rapidapi-key": "562ad42b0cmsh5d796280e44b680p1df165jsn6d71ed954e15"
            }
        })
        .then(response => app.imprimir(response.data))
        .catch(error => console.log(error));
    }

    return{
        getCountries:getCountries,
        getCountryByName:getCountryByName,
        getPos:getPos


    }
})();
