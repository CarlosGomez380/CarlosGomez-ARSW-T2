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
        axios({
            method:'get',
            url: "/coronavirus/"+ name,

        })
        .then(response => app.createTableByName(response.data.data.covid19Stats))
        .catch(error => console.log(error));
    }


    return{
        getCountries:getCountries,
        getCountryByName:getCountryByName

    }
})();
