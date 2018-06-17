function toggleIcon(e) {
    $(e.target)
        .prev('.dropdown-heading')
        .find(".more-less")
        .toggleClass('glyphicon-chevron-up glyphicon-chevron-down');
}
$('.dropdownPanel').on('hidden.bs.collapse', toggleIcon);
$('.dropdownPanel').on('shown.bs.collapse', toggleIcon);