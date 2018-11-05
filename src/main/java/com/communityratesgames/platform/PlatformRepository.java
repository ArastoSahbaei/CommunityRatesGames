
package com.communityratesgames.platform;

import org.springframework.data.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatformRepository extends CrudRepository<Platform, Integer> {
}
