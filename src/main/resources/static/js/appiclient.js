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
        document.getElementById("Table").style.display="block";
        document.getElementById("TableInfo").style.display="block";
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

    return{
        getCountries:getCountries,
        getCountryByName:getCountryByName

    }
})();
