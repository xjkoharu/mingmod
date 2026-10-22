# 中国大陆网络环境下的构建说明

`maven.neoforged.net` 在国内经常无法访问（TLS SNI 被重置、本地 DNS 被污染）。
本仓库**不**硬编码任何镜像地址，镜像配置放在**本机**的 Gradle init 脚本里，
不会进版本库，也不会影响其他贡献者。

## 一次性配置（本机）

1. 确认 CDN 边缘域名（`maven.neoforged.net` 的 CNAME 目标）：

   ```bash
   curl -H 'accept: application/dns-json' \
        'https://doh.pub/dns-query?name=maven.neoforged.net&type=CNAME'
   ```

   形如 `1755624453.rsc.cdn77.org.`。

2. 在 `~/.gradle/init.d/` 下放一个 init 脚本，把所有指向
   `maven.neoforged.net` 的仓库 URL 改写成该边缘域名（保留路径）：

   ```groovy
   def CDN_HOST = '1755624453.rsc.cdn77.org'
   def rewrite = { repo ->
       if (repo instanceof MavenArtifactRepository) {
           def url = repo.url.toString()
           if (url.contains('maven.neoforged.net')) repo.url = url.replace('maven.neoforged.net', CDN_HOST)
       }
   }
   def install = { c -> c.all(rewrite); c.whenObjectAdded(rewrite) }
   settingsEvaluated { s ->
       install(s.pluginManagement.repositories)
       try { install(s.dependencyResolutionManagement.repositories) } catch (ignored) {}
   }
   gradle.beforeProject { p -> install(p.buildscript.repositories); install(p.repositories) }
   gradle.afterProject  { p -> install(p.buildscript.repositories); install(p.repositories) }
   ```

   注意插件会用三个仓库路径：`/releases`、`/snapshots`、`/mojang-meta`，
   所以要**整域替换**，不能只替换 `/releases`。

## 其他慢源

- Gradle 发行版：`mirrors.cloud.tencent.com/gradle/gradle-9.2.1-bin.zip`，
  解压到 `~/.gradle/wrapper/dists/gradle-9.2.1-bin/<hash>/` 即可复用。
- JDK 21：`mirrors.tuna.tsinghua.edu.cn/Adoptium/21/jdk/x64/windows/`。
- Parchment 映射：`maven.parchmentmc.org` 会 302 到
  `ldtteam.jfrog.io`，可直连。
- Mojang 资源：`libraries.minecraft.net`、`piston-meta.mojang.com` 通常可直连。

## 有可用代理时

清空或删掉该 init 脚本即可，Gradle 会走系统代理（`https_proxy` 环境变量或
`gradle.properties` 里的 `systemProp.https.proxyHost/Port`）。
