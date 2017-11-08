grails.project.work.dir = 'target'
grails.project.class.dir = 'target/classes'
grails.project.test.class.dir = 'target/test-classes'
grails.project.test.reports.dir = 'target/test-reports'
grails.project.dependency.resolution = {

	inherits 'global'
	log 'warn'

	repositories {
		grailsPlugins()
		grailsHome()
		grailsCentral()
		mavenCentral()
	}

	def gebVersion = '0.9.2'
	def seleniumVersion = '2.41.0'

	dependencies {
		test "org.seleniumhq.selenium:selenium-chrome-driver:$seleniumVersion",
				"org.seleniumhq.selenium:selenium-support:$seleniumVersion",
				"org.gebish:geb-spock:$gebVersion",
				"org.grails:grails-datastore-test-support:1.0.2-grails-2.4",
				"com.mashape.unirest:unirest-java:1.3.8",
				'org.hamcrest:hamcrest-all:1.3',
				"org.jodd:jodd-lagarto:3.4.1",
				"org.grails:grails-web-databinding-spring:$grailsVersion",
				'org.springframework:spring-expression:4.1.9.RELEASE',
				'org.springframework:spring-aop:4.1.9.RELEASE'
		compile 'org.jadira.usertype:usertype.jodatime:2.0.1'
		runtime 'org.springframework:spring-expression:4.1.9.RELEASE',
                'org.springframework:spring-aop:4.1.9.RELEASE'
	}
	plugins {
		build ":tomcat:7.0.70"

        //noinspection GroovyAssignabilityCheck
        runtime ":fields:1.3",
				":hibernate4:4.3.10"
        //noinspection GroovyAssignabilityCheck
		test ':build-test-data:2.4.0',
				':fixtures:1.3',
				":geb:$gebVersion"
        compile ":scaffolding:2.1.2"
    }
}

grails.plugin.location.'joda-time' = '../../..'
