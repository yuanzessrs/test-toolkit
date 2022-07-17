package pers.zengsx.toolkit.test.sdk.spock_support;

import lombok.extern.slf4j.Slf4j;
import org.spockframework.runtime.extension.IGlobalExtension;
import org.spockframework.runtime.model.SpecInfo;

/**
 * @class-name: SpockGlobalExtension
 * @description: spock lifecycle extend
 * https://spockframework.org/spock/docs/2.1/all_in_one.html#_getting_started
 * https://github.com/spockframework/spock/wiki/Third-Party-Extensions
 * @author: Mr.Zeng
 * @date: 2022-07-12 13:39
 */
@Slf4j
public class SpockGlobalExtension implements IGlobalExtension {

    @Override
    public void visitSpec(SpecInfo spec) {
        // Extend the Spock life cycle
    }

}
