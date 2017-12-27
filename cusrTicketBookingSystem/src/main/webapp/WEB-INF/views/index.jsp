<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- Static content -->

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
 <script src="https://apis.google.com/js/platform.js" async defer></script>
<link href='https://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
<link rel="stylesheet" type="text/css" href="/resources/css/style.css">
<link rel="stylesheet/scss" type="text/css" href="/resources/scss/style.scss">
<script type="text/javascript" src="/resources/js/index.js"></script>
<script src="https://www.gstatic.com/firebasejs/4.7.0/firebase.js"></script>
<script src='https://cdn.firebase.com/js/client/2.2.1/firebase.js'></script>
<link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Roboto:100,100i,400">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<script>

var provider = new firebase.auth.GoogleAuthProvider();
 var user,email,name;
 
function signIn(){
	console.log("called");
firebase.auth().signInWithPopup(provider).then(function(result) {
	  // This gives you a Google Access Token. You can use it to access the Google API.
	  var token = result.credential.accessToken;
	  // The signed-in user info.

	   	user = result.user;
	 	name = user.displayName;
 	 	email = user.email;
  		photoUrl = user.photoURL;
  		emailVerified = user.emailVerified;
  		
  		console.log("name in fucntion" +name);
	     console.log("email in function "+email);
	     
	     window.location.href="/welcome?name="+name+"&email="+email;
	  // ...
	}).catch(function(error) {
	  // Handle Errors here.
	  var errorCode = error.code;
	  var errorMessage = error.message;
	  // The email of the user's account used.
	  var email = error.email;
	  // The firebase.auth.AuthCredential type that was used.
	  var credential = error.credential;
	  // ...
	});
	console.log("name" +name);
    console.log("email "+email);	
  
}

function signout(){
	firebase.auth().signOut().then(function() {
		  // Sign-out successful.
		}).catch(function(error) {
		  // An error happened.
		});
}


</script>

<script>
  // Initialize Firebase
  var config = {
    apiKey: "AIzaSyCQJmszXLrsU6ix80qri4RcEKWzsNupzrc",
    authDomain: "cusr-2e455.firebaseapp.com",
    databaseURL: "https://cusr-2e455.firebaseio.com",
    projectId: "cusr-2e455",
    storageBucket: "cusr-2e455.appspot.com",
    messagingSenderId: "197405183947"
  };
  firebase.initializeApp(config);
</script>

<body>
  <div class="form">
      
      <ul class="tab-group">
        <li class="tab active"><a href="#signup">Sign Up</a></li>
        <li class="tab "><a href="#login">Log In</a></li>
      </ul>
      
      <div class="tab-content">
        <div id="signup">   
          <h1>Sign Up for Free</h1>
          
          <form action="/newlogin" method="post">
          
          <div class="top-row">
            <div class="field-wrap">
              <label>
                Name<span class="req">*</span>
              </label>
              <input id="name" name="name" type="text" required autocomplete="off" />
            </div>
        
            <div class="field-wrap">
              
            </div>
          </div>

          <div class="field-wrap">
            <label>
              Email Address<span class="req">*</span>
            </label>
            <input id="email" name="email" type="email" required autocomplete="off"/>
          </div>
          
          <div class="field-wrap">
            <label>
              Set A Password<span class="req">*</span>
            </label>
            <input id="password" name="password" type="password"required autocomplete="off"/>
          </div>
          
          <button type="submit" class="button button-block">Get Started</button>
          </form>
          <p></p>
          <h1>OR!</h1> 
          
           <button class="loginBtn loginBtn--google" action="/home" onclick="signIn()">
  				SignUp with Google
			</button>


          

        </div>
        
        <div id="login">   
          <h1>Welcome Back!</h1>
          
          <form action="/signin" method="post">
          
            <div class="field-wrap">
            <label>
              Email Address<span class="req">*</span>
            </label>
            <input id="email_l" name="email_l" type="email"required autocomplete="off"/>
          </div>
          
          <div class="field-wrap">
            <label>
              Password<span class="req">*</span>
            </label>
            <input id="password_l" name="password_l" type="password"required autocomplete="off"/>
          </div>
                    
        
          
          
          <button  type="submit" class="button button-block">Log In</button>
             </form>
          <p></p>
          <h1>OR!</h1>
         
         
          
           <button class="loginBtn loginBtn--google" onclick="signIn()">
  Login with Google
</button>
         
         

        </div>
        
      </div><!-- tab-content -->
      
</div> <!-- /form -->
  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script>

$('.form').find('input, textarea').on('keyup blur focus', function (e) {
	  
	  var $this = $(this),
	      label = $this.prev('label');

		  if (e.type === 'keyup') {
				if ($this.val() === '') {
	          label.removeClass('active highlight');
	        } else {
	          label.addClass('active highlight');
	        }
	    } else if (e.type === 'blur') {
	    	if( $this.val() === '' ) {
	    		label.removeClass('active highlight'); 
				} else {
			    label.removeClass('highlight');   
				}   
	    } else if (e.type === 'focus') {
	      
	      if( $this.val() === '' ) {
	    		label.removeClass('highlight'); 
				} 
	      else if( $this.val() !== '' ) {
			    label.addClass('highlight');
				}
	    }

	});

	$('.tab a').on('click', function (e) {
	  
	  e.preventDefault();
	  
	  $(this).parent().addClass('active');
	  $(this).parent().siblings().removeClass('active');
	  
	  target = $(this).attr('href');

	  $('.tab-content > div').not(target).hide();
	  
	  $(target).fadeIn(600);
	  
	});
</script>
    

</body>
</html>
