package com.jothapunkt.spigot.raftcraft.util;

public enum Skins implements Skin {
    FISHMAN(
        "ewogICJ0aW1lc3RhbXAiIDogMTcyNTk5MTk4MDQyMywKICAicHJvZmlsZUlkIiA6ICJlZGUyYzdhMGFjNjM0MTNiYjA5ZDNmMGJlZTllYzhlYyIsCiAgInByb2ZpbGVOYW1lIiA6ICJ0aGVEZXZKYWRlIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzEzYmI0ZGJkNjJlYjVlZjE3YTFkMTJlZDk2NGQ3ODI5ZDlkMGEzZDc4NGVhZDdhOGQ1OWMxMTljNDMyOGExZmQiCiAgICB9CiAgfQp9",
        "n1I4G3Ag02lhe+eMPDSiwFcNiZC3Rm9mwQWjgPCGtDdtnJiwPY7xwj//f8PfdaCGllrWM3ZiwrnUyK+6qvNH320xU2DwxpaOniqi7m2wxu0XputjF1Q/ma9IaCOlJ2cn9hOxb6MWyq91lu6pQHB7i5VFLAk4/VBuJBn4ZcDFsfomASD3fWW7eahmZuIsKjmGjWWiZr72dTCABA3NmpMY2Roy5i2tuDwlje12GOjovGL9fyDqOlv6rgo2f8+Wj+JaSyI7E2derH3bEnBa6QIPMhET+Aei8kkeWkY7zrmu2ksGclQWe56M9K/QLIPumV1YRgOtyy1WKKHyxeRtqqohGqJwSPgEKnLss8QZUJqJRT7GKY3xhcZSklTvPBBKlX6TGeXUktyeAbsrIiesHWvSNB0kszwUH8Sb6y4k5+HOVk4FbuWEgiMTvYxRRHEn0Ygzvq/Bmd7QKRbL5K5Vf5PeWl17r4iKweyJJSbv5QCeASasQIYaVtP7NdoUHR/k+Worjw4wRv5TUOxoYyTftJvJLGSXd71Ha2WEL04D+xIuMy76Pyzz5hWra9ReV/ajGO/s1X3wm05pX+xpwqmvRqyC7t+YgnOJ1ygmrjfFaN9JdkH9sphVigNyFZgZu/X0ovE8u5cgIVVLH99m/DJJdz2vQUyFf+k3dsksHMBx4teL3ts="
    );

    private String texture;
    private String signature;
    private Skins(String texture, String signature) {
        this.texture = texture;
        this.signature = signature;
    }
    @Override
    public String getTexture() {
        return texture;
    }
    @Override
    public String getSignature() {
        return signature;
    }
}
