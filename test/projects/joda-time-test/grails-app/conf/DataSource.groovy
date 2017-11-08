dataSource {
    pooled = true
    driverClassName = "org.h2.Driver"
    username = "sa"
    password = ""
}
hibernate {
    //show_sql = true
    namingStrategy = DefaultNamingStrategy
    cache.use_second_level_cache = false
    cache.use_query_cache = false
    cache.region.factory_class = 'org.hibernate.cache.ehcache.EhCacheRegionFactory' // Hibernate 4
//    cache.region.factory_class = 'grails.plugin.cache.ehcache.hibernate.BeanEhcacheRegionFactory' // Hibernate 3
    cache.provider_class = 'grails.plugin.cache.ehcache.hibernate.GrailsEhCacheManagerFactoryBean'
    cache.useCacheProvider = true
    singleSession = true   // do not open a hibernate session for every request
    flush.mode = "auto" // this is the default.  other values are "commit" and "manual"
    generate_statistics = true
}
// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = "create-drop" // one of 'create', 'create-drop','update'
            url = "jdbc:h2:mem:devDb"
        }
    }
    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:h2:mem:testDb"
        }
    }
    production {
        dataSource {
            dbCreate = "update"
            url = "jdbc:h2:prodDb"
        }
    }
}
