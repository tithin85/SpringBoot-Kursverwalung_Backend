
    $(document).ready(function() {
    $('#kursForm').submit(function(event) {
        // prevent the form from submitting normally
        event.preventDefault();

        var name = $('#name').val();
        var anzahlTage= $('#anzahlTage').val();
        var wieOftinWoche= $('#wieOftinWoche').val();
        var startDatum= $('#startDatum').val();
        var minTnZahl= $('#minTnZahl').val();
        var maxTnZahl= $('#maxTnZahl').val();
        var gebuehrBrutto= $('#gebuehrBrutto').val();
        var mwstProzent= $('#mwstProzent').val();
        var kursBeschreibung= $('#kursBeschreibung').val();
        var status= $('#status').val();


        $.ajax({
            url: '/kurs/add',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                name: name,
                anzahlTage: anzahlTage,
                wieOftinWoche:wieOftinWoche,
                startDatum:startDatum,
                minTnZahl:minTnZahl,
                maxTnZahl:maxTnZahl,
                gebuehrBrutto:gebuehrBrutto,
                mwstProzent:mwstProzent,
                kursBeschreibung:kursBeschreibung,
                status:status,
            }),
            success: function(response) {

                alert(response);
            },
            error: function(response) {

                console.log(response.responseJSON.message);
            }
        })
    })
})
