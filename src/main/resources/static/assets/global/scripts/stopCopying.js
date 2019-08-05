$(function() {
	$(document).bind('contextmenu', function(e) {
		e.preventDefault();
		tooltip("Right Click is not allowed", "error");
		click('right');
	});

	/*
	 * $('.Container').bind('contextmenu',function(e){ e.preventDefault();
	 * alert('Right Click is not allowed on div'); });
	 */
});

shortcut.add("Ctrl+B", function(e) {
	e.preventDefault();
	tooltip("The bookmarks of your browser will show up after this alert",
			"error");
	click('Ctrl+B');
	/*
	 * document.getElementById("content").innerHTML = document
	 * .getElementById("content").innerHTML + "<br/>You have pressed Control +
	 * B"
	 */;
}, {
	'propagate' : false,
	'target' : document
});
shortcut.add("Ctrl+A", function(e) {
	e.preventDefault();
	tooltip("Keyboard shortcut Control + A pressed", "error");
	click('Ctrl+A');
	/*
	 * document.getElementById("content").innerHTML = document
	 * .getElementById("content").innerHTML + "<br/>You have pressed Control +
	 * A"
	 */;
});
shortcut.add("Ctrl+C", function(e) {
	e.preventDefault();
	tooltip("Copying prohibitted.", "error");
	click('Ctrl+C');
	// Ajax Call for database notification SMS to CTO
	/*
	 * document.getElementById("content").innerHTML = document
	 * .getElementById("content").innerHTML + "<br/>You are a cluprit and you
	 * tried to copy the content"
	 */;
});
shortcut.add("Ctrl+V", function(e) {
	e.preventDefault();
	tooltip("Keyboard shortcut Control + V pressed", "error");
	click('Ctrl+V');
	/*
	 * document.getElementById("content").innerHTML = document
	 * .getElementById("content").innerHTML + "<br/>You have pressed Control +
	 * A"
	 */;
});
shortcut.add("Ctrl+U", function(e) {
	e.preventDefault();
	tooltip("Keyboard shortcut Control + U pressed", "error");
	click('Ctrl+U');
	/*
	 * document.getElementById("content").innerHTML = document
	 * .getElementById("content").innerHTML + "<br/>You have pressed Control +
	 * A"
	 */;
});
shortcut.add("Ctrl+S", function(e) {
	e.preventDefault();
	tooltip("Keyboard shortcut Control + S pressed", "error");
	click('Ctrl+S');
	/*
	 * document.getElementById("content").innerHTML = document
	 * .getElementById("content").innerHTML + "<br/>You have pressed Control +
	 * A"
	 */;
});
shortcut.add("PrtScn", function(e) {
	alert("pavan");
	e.preventDefault();
	tooltip("Copying using print screen detected ", "error");
	click('PrtScn');
	// Ajax Call for database notification SMS to CTO
	/*
	 * document.getElementById("content").innerHTML = document
	 * .getElementById("content").innerHTML + "<br/>Copying using print screen
	 * detected"
	 */;
});
/*
 * shortcut.add("Right", function() {
 * document.getElementById("content").innerHTML = document
 * .getElementById("content").innerHTML + "--> " ; }) shortcut.add("Left",
 * function() { document.getElementById("content").innerHTML = document
 * .getElementById("content").innerHTML + "<-- " ; }) shortcut.add("Up",
 * function() { document.getElementById("content").innerHTML = document
 * .getElementById("content").innerHTML + "^ "; }) shortcut.add("Down",
 * function() { document.getElementById("content").innerHTML = document
 * .getElementById("content").innerHTML + ". " ; })
 */
shortcut
		.add(
				"F1",
				function() {
					/*
					 * document.getElementById("content").innerHTML = "<br/>
					 * --- Help --- <p/> Right = --> <br/> Left = <-- <br/> Up = ^
					 * <br/> Down = . <br/> Ctrl+A = Message <br/> Ctrl+B =
					 * Bookmarks "
					 */;

				});
/*
 * shortcut.add("Tab", function() { document.getElementById("content").innerHTML =
 * document .getElementById("content").innerHTML + " | - - - - - - | "; });
 */

var context;
window.addEventListener('load', init, false);
function init() {
	try {
		// Fix up for prefixinG
		window.AudioContext = window.AudioContext || window.webkitAudioContext;
		context = new AudioContext();
		/*
		 * document.getElementById("content").innerHTML = document
		 * .getElementById("content").innerHTML + "Audio context initialized
		 * <br/>"
		 */;
	} catch (e) {
		// alert('Web Audio API is not supported in this browser');
	}
}

$(document).keyup(function(e) {
	if (e.keyCode == 44) {
		e.preventDefault();
		alert("Print screen pressed");
		return false;
	}

});
// Ctrl Alt Shift Meta (Command key on Macintosh keyboards)
// all alpha-numeric keys (a - z and 0 - 9)
// Tab
// Space
// Return
// Enter
// Backspace
// Scroll_lock
// Caps_lock
// /Num_lock
// Pause
// Insert
// Home
// Delete
// End
// Page_up
// Page_down
// Left
// Up
// Right
// Down
// F1 - F12
