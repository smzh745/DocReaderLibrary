# DocReaderLibrary



<p> Step 1. Add the JitPack repository to your build file <br/><br>
Add it in your root build.gradle at the end of repositories:</p>
<pre><code>allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
</code></pre>

<p>
Step 2. Add the dependency
</p>
<pre><code>
dependencies {
    implementation 'com.github.smzh745:DocReaderLibrary:1.0'
	}
</code></pre>

# How does it works

<p>Step 1: Use these permissions in the <b>AndroidManifest.xml</b> file. And remember to call these permissions as runtime permissions</p>

<p>
	android.permission.READ_EXTERNAL_STORAGE <br/>
	android.permission.WRITE_EXTERNAL_STORAGE
</p><br/>

<p>Step 2: Use below code just to call <b> (PPT files, DOC files, XLS files, TXT files) </b> <br/> Remember that, PPT= Powerpoint files, doc= Word files, xls= excel files </p>
