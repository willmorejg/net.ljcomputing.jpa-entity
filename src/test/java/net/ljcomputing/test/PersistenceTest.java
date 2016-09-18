/**
           Copyright 2016, James G. Willmore

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package net.ljcomputing.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.ljcomputing.config.PersistenceConfiguration;
import net.ljcomputing.entity.Person;
import net.ljcomputing.service.PersonService;

/**
 * Persistence test.
 * 
 * @author James G. Willmore
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@Import(PersistenceConfiguration.class)
public class PersistenceTest {

  /** The SLF4J Logger. */
  private static final Logger LOGGER = LoggerFactory.getLogger(PersistenceTest.class);
  
  /** The person service. */
  @Autowired
  private transient PersonService personService;

  /**
   * Test.
   */
  @Test
  @Transactional
  public void test() {
    List<Person> people = personService.readAll();
    LOGGER.debug("people: {}", people);

    final Person person = new Person();
    person.setName("jim");

    LOGGER.debug("creating person: {}", person);
    personService.createOrUpdate(person);

    validatePerson(person);
    
    people = personService.readAll();

    for (final Person p : people) {
      LOGGER.debug("-- person {}", p);
      p.setName("JOE");
      LOGGER.debug("-- NEW person {}", p);
      personService.createOrUpdate(person);

      validatePerson(p);
      assertEquals("name is not equal to JOE", "JOE", p.getName());

      LOGGER.debug("-->> UPDATED: {}", personService.readById(p.getId()));
      personService.delete(p);
    }
  }

  /**
   * Validate person.
   *
   * @param person the person
   */
  private void validatePerson(final Person person) {
    assertNotNull("id is null", person.getId());
    assertNotNull("created timestamp is null", person.getCreatedTs());
    assertNotNull("modified timestamp is null", person.getModifiedTs());
    assertNotNull("uuid is null", person.getUuid());
    assertNotNull("name is null", person.getName());
  }
}
