object Environment {
    private const val VERSION_MAJOR = 1
    private const val VERSION_MINOR = 0
    private const val VERSION_PATCH = 0
    private const val VERSION_BUILD = 1

    object Release {
        const val appId = "com.akerimtay.rickandmorty"
        const val appVersionCode = VERSION_BUILD
        const val appVersionName = "$VERSION_MAJOR.$VERSION_MINOR.$VERSION_PATCH"
    }

    object BuildTypes {
        const val release = "release"
        const val qa = "qa"
        const val debug = "debug"
    }
}