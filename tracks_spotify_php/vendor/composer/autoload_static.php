<?php

// autoload_static.php @generated by Composer

namespace Composer\Autoload;

class ComposerStaticInit4b40ad235d295a2585fc15459d18b090
{
    public static $prefixLengthsPsr4 = array (
        'S' => 
        array (
            'SpotifyWebAPI\\' => 14,
        ),
    );

    public static $prefixDirsPsr4 = array (
        'SpotifyWebAPI\\' => 
        array (
            0 => __DIR__ . '/..' . '/jwilsson/spotify-web-api-php/src',
        ),
    );

    public static $prefixesPsr0 = array (
        'U' => 
        array (
            'Unirest\\' => 
            array (
                0 => __DIR__ . '/..' . '/mashape/unirest-php/src',
            ),
        ),
    );

    public static function getInitializer(ClassLoader $loader)
    {
        return \Closure::bind(function () use ($loader) {
            $loader->prefixLengthsPsr4 = ComposerStaticInit4b40ad235d295a2585fc15459d18b090::$prefixLengthsPsr4;
            $loader->prefixDirsPsr4 = ComposerStaticInit4b40ad235d295a2585fc15459d18b090::$prefixDirsPsr4;
            $loader->prefixesPsr0 = ComposerStaticInit4b40ad235d295a2585fc15459d18b090::$prefixesPsr0;

        }, null, ClassLoader::class);
    }
}
