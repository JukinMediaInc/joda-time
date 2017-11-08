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

	def gebVersion = '0.13.1'
	def seleniumVersion = '3.7.1'

	dependencies {
		test "org.seleniumhq.selenium:selenium-chrome-driver:$seleniumVersion",
				"org.seleniumhq.selenium:selenium-support:$seleniumVersion",
				"org.gebish:geb-spock:$gebVersion",
				"com.mashape.unirest:unirest-java:1.3.8"
		compile 'org.jadira.usertype:usertype.jodatime:2.0.1'
	}
	plugins {
		build ":tomcat:7.0.70"
		runtime ":fields:1.3",
				":hibernate4:4.3.10"
		test ':build-test-data:2.4.0',
				':fixtures:1.3',
				":geb:$gebVersion"
        compile ":scaffolding:2.1.2"
    }
}

grails.plugin.location.'joda-time' = '../../..'
