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

package net.ljcomputing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * A Person domain.
 * 
 * @author James G. Willmore
 *
 */
@Entity
@Table(name = "PERSON")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Person extends AbstractPersistedEntity implements PersistedEntity {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 7177292967557438899L;

  /** The name. */
  @Column(name = "NAME", nullable = false)
  private String name;

  /**
   * Gets the name.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name.
   *
   * @param name the new name
   */
  public void setName(final String name) {
    this.name = name;
  }

  /**
   * @see net.ljcomputing.entity.AbstractPersistedEntity#toString()
   */
  @Override
  public String toString() {
    return "Person [" + super.toString() + ", name=" + name + "]";
  }
}
