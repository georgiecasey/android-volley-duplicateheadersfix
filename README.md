android-volley-duplicateheadersfix
==================================

Fix for this Stack Overflow question:
http://stackoverflow.com/questions/18998361/android-volley-duplicate-set-cookie-is-overriden
And discussed on the official Volley group over here:
https://groups.google.com/forum/#!topic/volley-users/rNTlV-LORzY

Android Volley returns response headers as a Map which overwrites duplicate keys. So you only get one 'Set-Cookie' header for example. I attempted to fix this by extending some classes but once I had to extend NetworkResponse, it proved too troublesome. So I just made direct edits to the Volley library at
NetworkResponse.java, BasicNetwork.java and HurlStack.java in this commit:
https://github.com/georgiecasey/android-volley-duplicateheadersfix/commit/9f3cf1cf5684ce788c2bfae260a8e0ebe6fa93ac#commitcomment-4461641

This adds an extra parameter to NetworkResponse with an array called apacheHeaders. You can access like this:
```
    			for (int i = 0; i < response.apacheHeaders.length; i++) {
    				String key = response.apacheHeaders[i].getName();
    				String value = response.apacheHeaders[i].getValue();
    				Log.d("VOLLEY_HEADERFIX",key + " - " +value);
    			}
```

An example usage Activity is in src/com/georgiecasey/DuplicateHeadersFixExample.java


