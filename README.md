## iconexe

This project injects .ico files into exe (PE) files.

There is an ant task (iconexe.ant.IconExeTask) for easy utilization in builds.

All it does is wrap this class:

http://git.eclipse.org/c/equinox/rt.equinox.p2.git/plain/bundles/org.eclipse.equinox.p2.publisher.eclipse/src/org/eclipse/pde/internal/swt/tools/IconExe.java

## Ant example

./project/build.xml
```
<loadproperties srcFile="${user.home}/creds/secret.properties"/>
<taskdef name="iconexe" classname="iconexe.ant.IconExeTask" classpath="iconexe.jar"/>
<iconexe exeFile="${dist.dir}/exe/service.exe" icoFile="${dist.dir}/icon.ico"/>
```

## building

This uses the [cfdistro](https://github.com/cfmlprojects/cfdistro) project, which adds a bunch of ant tasks and stuff.  If you're feeling lucky, just type:
```
./iconexe build.artifact
```

To build and add the resulting artifact to your local cfdistro repo.  If you run into any problems you can convert build/build.xml to a normal ant build pretty easy-- it's nothing too special beyond the thing that defines a taskdef using a dependency (which *is* pretty tits), I reckon.

If you import it into eclipse run the build once and it'll fetch the dependencies, resolving the classpath.
