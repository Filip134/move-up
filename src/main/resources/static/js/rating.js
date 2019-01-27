var starsCount;

function StarRating()
{
    this.init();
};

StarRating.prototype.init = function()
{
    this.stars = document.querySelectorAll('#rating span');
    for (var i = 0; i < this.stars.length; i++)
    {
        this.stars[i].setAttribute('data-count', i);
        this.stars[i].addEventListener('mouseenter', this.enterStarListener.bind(this));
    }

    document.querySelector('#rating').addEventListener('mouseleave', this.leaveStarListener.bind(this));
    document.querySelector('#rating').addEventListener('click', this.clickStarListener.bind(this));
};


StarRating.prototype.enterStarListener = function(e)
{
    this.fillStarsUpToElement(e.target);
};

StarRating.prototype.leaveStarListener = function()
{
    this.fillStarsUpToElement(null);
};

StarRating.prototype.clickStarListener = function()
{
    var ratingForm = document.getElementById("ratingForm");
    var stars = document.getElementById("stars");
    var eventDate = new Date(document.getElementById("dateFormat").innerText);
    var currentDate = new Date();

    // if(eventDate > currentDate)
    // {
    //     setTimeout(function(){
    //         document.getElementById("notHappened").style.display = "none";
    //         //do what you need here
    //     }, 2000);
    //     document.getElementById("notHappened").style.display = "block";
    // }
    // else
    {
        stars.value = starsCount;
        ratingForm.submit();
    }
}

StarRating.prototype.fillStarsUpToElement = function(el)
{
    for (var i = 0; i < this.stars.length; i++)
    {
        if (el == null || this.stars[i].getAttribute('data-count') > el.getAttribute('data-count'))
        {
            this.stars[i].classList.remove('hover');
        }
        else
        {
            starsCount = i+1;
            this.stars[i].classList.add('hover');
        }
    }
};

new StarRating();

