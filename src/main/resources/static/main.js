// var auto_refresh = setInterval(function () {
//     $('#user-table').fadeOut('slow', function() {
//         $(this).load('/echo/json/', function() {
//             $(this).fadeIn('slow');
//         });
//     });
// }, 15000); // refresh every 15000 milliseconds

// when DOM is ready
$(document).ready(function () {

    $(function () {
        $.ajax(
            {
                type: "GET",
                url: '/port',
                dataType: "json",
                success: function (ports) {
                    $.each(ports, function (i, port) {
                        $('#depPointInput').append($("<option>").append(port.name));
                        $('#destPointInput').append($("<option>").append(port.name));
                    })
                }
            });
    });

    $(function () {
        $.ajax(
            {
                type: "GET",
                url: '/company',
                dataType: "json",
                success: function (companies) {
                    for (let i = 0; i < companies.length; i++) {
                        var $chk = $('<label class="checkbox-inline"><input type="checkbox" id="' + companies[i].name + '" name="companyChk"  value="' + companies[i].name + '"/>' + companies[i].name + '</label>');
                        $("#box").append($chk);
                    }
                }
            });
    });

    $(function () {

        $('#findFlightBtn').on('click', function () {
            var departurePoint = $('#depPointInput').val();
            var destinationPoint = $('#destPointInput').val();
            var company = []
            $("input:checkbox[name=companyChk]:checked").each(function () {
                company.push($(this).val());
            });

            var formData = new FormData();
            formData.append("departurePoint", departurePoint);
            formData.append("destinationPoint", destinationPoint);
            formData.append("company", company);
            $.ajax(
                {
                    type: 'POST',
                    url: '/avia/find',
                    data: formData,
                    contentType: false,
                    processData: false,
                    success: function (ports) {
                        $("#flight-table > tbody").empty();
                        $("#distanceSpan").empty();
                        $("#priceSpan").empty();
                        $('#myPanel').empty();

                        for (let i = 0; i < ports.length; i++) {
                            $('#flight-table').append($('<tr class="success">')
                                .append($("<td>").append(""))
                                .append($("<td>").append(""))
                                .append($("<td>").append(""))
                                .append($("<td>").append(""))
                                .append($("<td>").append(""))
                                .append($("<td>").append(""))
                                .append($("<td>").append("")));
                            var dto = ports[i];

                            function number() {
                                if (i === 0) {
                                    return "first"
                                } else if (i === 1) {
                                    return "second"
                                } else if (i === 2) {
                                    return "third"
                                } else if (i === 3) {
                                    return "fourth"
                                } else if (i === 4) {
                                    return "fifth"
                                }
                            }

                            $('#myPanel').append($('<div class="panel-body">').append($('<h4>Total price for ' + number() + '' + ' cheapest flight from ' + departurePoint + ' to ' + destinationPoint + ' ' + dto.totalPrice + ' and total distance is ' + dto.totalDistance + ' <small></small></h4>')));

                            for (let j = 0; j < dto.dtoList.length; j++) {
                                $('#flight-table').append($('<tr>')
                                    .append($("<td>").append(dto.dtoList[j].id))
                                    .append($("<td>").append(dto.dtoList[j].departurePoint))
                                    .append($("<td>").append(dto.dtoList[j].destinationPoint))
                                    .append($("<td>").append(dto.dtoList[j].company))
                                    .append($("<td>").append(dto.dtoList[j].price))
                                    .append($("<td>").append(dto.dtoList[j].distance))
                                    .append($("<td>").append(dto.dtoList[j].time)));
                            }
                        }
                        $('#table-body tr:first').remove();
                    },
                    error: function (jqXhr, textStatus, errorThrown) {
                        console.log(errorThrown);
                    }
                });
        });
    });

});

