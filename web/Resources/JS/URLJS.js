function getUrlVariable(variable)
{
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i=0;i<vars.length;i++) {
        var pair = vars[i].split("=");
        if(pair[0] == variable){return pair[1];}
    }
    return(false);
}

function getBookUrl(){
        var id = $(this).attr('value');
        var faculty = getUrlVariable('faculty');
        return '${pageContext.request.contextPath}/main.jsp?faculty='+faculty +'&classes=' + id;
}