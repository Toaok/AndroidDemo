    **********************************************************************************************
    1.Unable to find a matching configuration of project :Themvp:
      - Configuration 'debugApiElements':
          - Required com.android.build.api.attributes.BuildTypeAttr 'dev' and found incompatible value 'debug'.
          - Found com.android.build.api.attributes.VariantAttr 'debug' but wasn't required.
          - Required com.android.build.gradle.internal.dependency.AndroidTypeAttr 'Aar' and found compatible value 'Aar'.
          - Required efault 'androidDemo' but no value provided.
          - Required org.gradle.usage 'java-runtime' and found incompatible value 'java-api'.
      - Configuration 'debugRuntimeElements':
          - Required com.android.build.api.attributes.BuildTypeAttr 'dev' and found incompatible value 'debug'.
          - Found com.android.build.api.attributes.VariantAttr 'debug' but wasn't required.
          - Required com.android.build.gradle.internal.dependency.AndroidTypeAttr 'Aar' and found compatible value 'Aar'.
          - Required efault 'androidDemo' but no value provided.
          - Required org.gradle.usage 'java-runtime' and found compatible value 'java-runtime'.
      - Configuration 'releaseApiElements':
          - Required com.android.build.api.attributes.BuildTypeAttr 'dev' and found incompatible value 'release'.
          - Found com.android.build.api.attributes.VariantAttr 'release' but wasn't required.
          - Required com.android.build.gradle.internal.dependency.AndroidTypeAttr 'Aar' and found compatible value 'Aar'.
          - Required efault 'androidDemo' but no value provided.
          - Required org.gradle.usage 'java-runtime' and found incompatible value 'java-api'.
      - Configuration 'releaseRuntimeElements':
          - Required com.android.build.api.attributes.BuildTypeAttr 'dev' and found incompatible value 'release'.
          - Found com.android.build.api.attributes.VariantAttr 'release' but wasn't required.
          - Required com.android.build.gradle.internal.dependency.AndroidTypeAttr 'Aar' and found compatible value 'Aar'.
          - Required efault 'androidDemo' but no value provided.
          - Required org.gradle.usage 'java-runtime' and found compatible value 'java-runtime'.
    **********************************************************************************************
    2. Unable to resolve dependency for ':app@androidDemoDev/compileClasspath': Could not resolve project :Themvp.
            Open File
            Show Details
    **********************************************************************************************
    原因：android studio 3.0以后出现的，老版本没有该问题，出现以上两种问题是由于依赖库和被依赖库中拥有不同元素的buildTypes
    解决方案：被依赖的模块的buildTypes种类必须是依赖模块的超集