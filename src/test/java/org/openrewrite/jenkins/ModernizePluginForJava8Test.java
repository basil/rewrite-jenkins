/*
 * Copyright 2023 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * https://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openrewrite.jenkins;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openrewrite.test.RecipeSpec;
import org.openrewrite.test.RewriteTest;

import static org.openrewrite.maven.Assertions.pomXml;

class ModernizePluginForJava8Test implements RewriteTest {
    @Override
    public void defaults(RecipeSpec spec) {
        spec.recipeFromResource("/META-INF/rewrite/java-8.yml", "org.openrewrite.jenkins.ModernizePluginForJava8");
    }

    @Test
    void shouldUseHttpsForRepositories() {
        rewriteRun(pomXml(
                """
                        <project>
                            <parent>
                                <groupId>org.jenkins-ci.plugins</groupId>
                                <artifactId>plugin</artifactId>
                                <version>4.51</version>
                                <relativePath/>
                            </parent>
                            <properties>
                                <jenkins.version>2.346.3</jenkins.version>
                            </properties>
                            <repositories>
                                <repository>
                                    <id>repo.jenkins-ci.org</id>
                                    <url>http://repo.jenkins-ci.org/public/</url>
                                </repository>
                            </repositories>
                        </project>
                        """.stripIndent(),
                """
                        <project>
                            <parent>
                                <groupId>org.jenkins-ci.plugins</groupId>
                                <artifactId>plugin</artifactId>
                                <version>4.51</version>
                                <relativePath/>
                            </parent>
                            <properties>
                                <jenkins.version>2.346.3</jenkins.version>
                            </properties>
                            <repositories>
                                <repository>
                                    <id>repo.jenkins-ci.org</id>
                                    <url>https://repo.jenkins-ci.org/public/</url>
                                </repository>
                            </repositories>
                        </project>
                        """.stripIndent()
        ));
    }

    @Test
    void shouldUpgradeParentTo4_51() {
        rewriteRun(pomXml(
                """
                        <project>
                            <parent>
                                <groupId>org.jenkins-ci.plugins</groupId>
                                <artifactId>plugin</artifactId>
                                <version>4.40</version>
                                <relativePath/>
                            </parent>
                            <properties>
                                <jenkins.version>2.346.3</jenkins.version>
                            </properties>
                            <repositories>
                                <repository>
                                    <id>repo.jenkins-ci.org</id>
                                    <url>https://repo.jenkins-ci.org/public/</url>
                                </repository>
                            </repositories>
                        </project>
                        """.stripIndent(),
                """
                        <project>
                            <parent>
                                <groupId>org.jenkins-ci.plugins</groupId>
                                <artifactId>plugin</artifactId>
                                <version>4.51</version>
                                <relativePath/>
                            </parent>
                            <properties>
                                <jenkins.version>2.346.3</jenkins.version>
                            </properties>
                            <repositories>
                                <repository>
                                    <id>repo.jenkins-ci.org</id>
                                    <url>https://repo.jenkins-ci.org/public/</url>
                                </repository>
                            </repositories>
                        </project>
                        """.stripIndent()
        ));
    }

    @Test
    void shouldNotDowngradeParent() {
        rewriteRun(pomXml(
                """
                        <project>
                            <parent>
                                <groupId>org.jenkins-ci.plugins</groupId>
                                <artifactId>plugin</artifactId>
                                <version>4.52</version>
                                <relativePath/>
                            </parent>
                            <properties>
                                <jenkins.version>2.346.3</jenkins.version>
                            </properties>
                            <repositories>
                                <repository>
                                    <id>repo.jenkins-ci.org</id>
                                    <url>https://repo.jenkins-ci.org/public/</url>
                                </repository>
                            </repositories>
                        </project>
                        """.stripIndent()
        ));
    }

    @Test
    void shouldUpgradeJenkinsTo2_346_3() {
        rewriteRun(pomXml(
                """
                        <project>
                            <parent>
                                <groupId>org.jenkins-ci.plugins</groupId>
                                <artifactId>plugin</artifactId>
                                <version>4.51</version>
                                <relativePath/>
                            </parent>
                            <properties>
                                <jenkins.version>2.277.1</jenkins.version>
                            </properties>
                            <repositories>
                                <repository>
                                    <id>repo.jenkins-ci.org</id>
                                    <url>https://repo.jenkins-ci.org/public/</url>
                                </repository>
                            </repositories>
                        </project>
                        """.stripIndent(),
                """
                        <project>
                            <parent>
                                <groupId>org.jenkins-ci.plugins</groupId>
                                <artifactId>plugin</artifactId>
                                <version>4.51</version>
                                <relativePath/>
                            </parent>
                            <properties>
                                <jenkins.version>2.346.3</jenkins.version>
                            </properties>
                            <repositories>
                                <repository>
                                    <id>repo.jenkins-ci.org</id>
                                    <url>https://repo.jenkins-ci.org/public/</url>
                                </repository>
                            </repositories>
                        </project>
                        """.stripIndent()
        ));
    }

    @Test
    void shouldNotDowngradeJenkins() {
        rewriteRun(pomXml(
                """
                        <project>
                            <parent>
                                <groupId>org.jenkins-ci.plugins</groupId>
                                <artifactId>plugin</artifactId>
                                <version>4.51</version>
                                <relativePath/>
                            </parent>
                            <properties>
                                <jenkins.version>2.346.3</jenkins.version>
                            </properties>
                            <repositories>
                                <repository>
                                    <id>repo.jenkins-ci.org</id>
                                    <url>https://repo.jenkins-ci.org/public/</url>
                                </repository>
                            </repositories>
                        </project>
                        """.stripIndent()
        ));
    }

    @Test
    void shouldRemoveJavaLevelProperty() {
        rewriteRun(pomXml(
                """
                        <project>
                            <parent>
                                <groupId>org.jenkins-ci.plugins</groupId>
                                <artifactId>plugin</artifactId>
                                <version>4.51</version>
                                <relativePath/>
                            </parent>
                            <properties>
                                <java.level>8</java.level>
                                <jenkins.version>2.346.3</jenkins.version>
                            </properties>
                            <repositories>
                                <repository>
                                    <id>repo.jenkins-ci.org</id>
                                    <url>https://repo.jenkins-ci.org/public/</url>
                                </repository>
                            </repositories>
                        </project>
                        """.stripIndent(),
                """
                        <project>
                            <parent>
                                <groupId>org.jenkins-ci.plugins</groupId>
                                <artifactId>plugin</artifactId>
                                <version>4.51</version>
                                <relativePath/>
                            </parent>
                            <properties>
                                <jenkins.version>2.346.3</jenkins.version>
                            </properties>
                            <repositories>
                                <repository>
                                    <id>repo.jenkins-ci.org</id>
                                    <url>https://repo.jenkins-ci.org/public/</url>
                                </repository>
                            </repositories>
                        </project>
                        """.stripIndent()
        ));
    }

    @Test
    void shouldDisableLocalResolutionForParentPom() {
        rewriteRun(pomXml(
                """
                        <project>
                            <parent>
                                <groupId>org.jenkins-ci.plugins</groupId>
                                <artifactId>plugin</artifactId>
                                <version>4.51</version>
                            </parent>
                            <properties>
                                <jenkins.version>2.346.3</jenkins.version>
                            </properties>
                            <repositories>
                                <repository>
                                    <id>repo.jenkins-ci.org</id>
                                    <url>https://repo.jenkins-ci.org/public/</url>
                                </repository>
                            </repositories>
                        </project>
                        """.stripIndent(),
                """
                        <project>
                            <parent>
                                <groupId>org.jenkins-ci.plugins</groupId>
                                <artifactId>plugin</artifactId>
                                <version>4.51</version>
                                <relativePath/>
                            </parent>
                            <properties>
                                <jenkins.version>2.346.3</jenkins.version>
                            </properties>
                            <repositories>
                                <repository>
                                    <id>repo.jenkins-ci.org</id>
                                    <url>https://repo.jenkins-ci.org/public/</url>
                                </repository>
                            </repositories>
                        </project>
                        """.stripIndent()
        ));
    }

    @Test
    void shouldRemoveVersionsOnlyIfPresentInJenkinsBom() {
        rewriteRun(pomXml(
                """
                        <project>
                            <parent>
                                <groupId>org.jenkins-ci.plugins</groupId>
                                <artifactId>plugin</artifactId>
                                <version>4.51</version>
                            </parent>
                            <properties>
                                <jenkins.version>2.346.3</jenkins.version>
                            </properties>
                            <repositories>
                                <repository>
                                    <id>repo.jenkins-ci.org</id>
                                    <url>https://repo.jenkins-ci.org/public/</url>
                                </repository>
                            </repositories>
                            <dependencies>
                                <dependency>
                                    <groupId>junit</groupId>
                                    <artifactId>junit</artifactId>
                                    <version>4.4</version>
                                    <scope>test</scope>
                                </dependency>
                                <dependency>
                                    <groupId>org.jenkins-ci</groupId>
                                    <artifactId>annotation-indexer</artifactId>
                                    <version>1.16</version>
                                </dependency>
                                <dependency>
                                    <groupId>com.lmax</groupId>
                                    <artifactId>disruptor</artifactId>
                                    <version>3.3.11</version>
                                </dependency>
                            </dependencies>
                        </project>
                        """.stripIndent(),
                """
                        <project>
                            <parent>
                                <groupId>org.jenkins-ci.plugins</groupId>
                                <artifactId>plugin</artifactId>
                                <version>4.51</version>
                                <relativePath/>
                            </parent>
                            <properties>
                                <jenkins.version>2.346.3</jenkins.version>
                            </properties>
                            <repositories>
                                <repository>
                                    <id>repo.jenkins-ci.org</id>
                                    <url>https://repo.jenkins-ci.org/public/</url>
                                </repository>
                            </repositories>
                            <dependencies>
                                <dependency>
                                    <groupId>junit</groupId>
                                    <artifactId>junit</artifactId>
                                    <scope>test</scope>
                                </dependency>
                                <dependency>
                                    <groupId>org.jenkins-ci</groupId>
                                    <artifactId>annotation-indexer</artifactId>
                                </dependency>
                                <dependency>
                                    <groupId>com.lmax</groupId>
                                    <artifactId>disruptor</artifactId>
                                    <version>3.3.11</version>
                                </dependency>
                            </dependencies>
                        </project>
                        """.stripIndent()
        ));
    }

    @Test
    @Disabled
    void shouldUpgradeMajorVersion() {
        rewriteRun(pomXml(
                """
                        <project>
                            <parent>
                                <groupId>org.jenkins-ci.plugins</groupId>
                                <artifactId>plugin</artifactId>
                                <version>3.40</version>
                            </parent>
                            <artifactId>permissive-script-security</artifactId>
                            <version>0.8-SNAPSHOT</version>
                            <properties>
                                <java.level>8</java.level>
                                <jenkins.version>2.107.3</jenkins.version>
                            </properties>
                            <repositories>
                                <repository>
                                    <id>repo.jenkins-ci.org</id>
                                    <url>http://repo.jenkins-ci.org/public/</url>
                                </repository>
                            </repositories>
                        </project>
                        """.stripIndent(),
                """
                        <project>
                            <parent>
                                <groupId>org.jenkins-ci.plugins</groupId>
                                <artifactId>plugin</artifactId>
                                <version>4.64</version>
                                <relativePath/>
                            </parent>
                            <artifactId>permissive-script-security</artifactId>
                            <version>0.8-SNAPSHOT</version>
                            <properties>
                                <jenkins.version>2.332.1</jenkins.version>
                            </properties>
                            <repositories>
                                <repository>
                                    <id>repo.jenkins-ci.org</id>
                                    <url>https://repo.jenkins-ci.org/public/</url>
                                </repository>
                            </repositories>
                        </project>
                        """.stripIndent()
        ));
    }

    @Test
    @Disabled
    void shouldHandlePluginInBom() {
        rewriteRun(pomXml(
                """
                        <project>
                            <parent>
                                <groupId>org.jenkins-ci.plugins</groupId>
                                <artifactId>plugin</artifactId>
                                <version>4.42</version>
                                <relativePath />
                            </parent>
                            <artifactId>permissive-script-security</artifactId>
                            <version>0.8-SNAPSHOT</version>
                            <properties>
                                <jenkins.version>2.303.3</jenkins.version>
                            </properties>
                            <dependencies>
                                <dependency>
                                    <groupId>org.jenkins-ci.plugins</groupId>
                                    <artifactId>junit</artifactId>
                                    <version>1.12</version>
                                </dependency>
                            </dependencies>
                            <repositories>
                                <repository>
                                    <id>repo.jenkins-ci.org</id>
                                    <url>https://repo.jenkins-ci.org/public/</url>
                                </repository>
                            </repositories>
                        </project>
                        """.stripIndent(),
                """
                        <project>
                            <parent>
                                <groupId>org.jenkins-ci.plugins</groupId>
                                <artifactId>plugin</artifactId>
                                <version>4.42</version>
                                <relativePath />
                            </parent>
                            <artifactId>permissive-script-security</artifactId>
                            <version>0.8-SNAPSHOT</version>
                            <properties>
                                <jenkins.version>2.303.3</jenkins.version>
                            </properties>
                            <dependencyManagement>
                                <dependencies>
                                    <dependency>
                                        <groupId>io.jenkins.tools.bom</groupId>
                                        <artifactId>bom-2.303.x</artifactId>
                                        <version>1409.v7659b_c072f18</version>
                                        <type>pom</type>
                                        <scope>import</scope>
                                    </dependency>
                                </dependencies>
                            </dependencyManagement>
                            <dependencies>
                                <dependency>
                                    <groupId>org.jenkins-ci.plugins</groupId>
                                    <artifactId>junit</artifactId>
                                </dependency>
                            </dependencies>
                            <repositories>
                                <repository>
                                    <id>repo.jenkins-ci.org</id>
                                    <url>https://repo.jenkins-ci.org/public/</url>
                                </repository>
                            </repositories>
                        </project>
                        """.stripIndent()
        ));
    }
}
