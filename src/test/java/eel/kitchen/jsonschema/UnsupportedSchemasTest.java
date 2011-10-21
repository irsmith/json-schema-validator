/*
 * Copyright (c) 2011, Francis Galiegue <fgaliegue@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package eel.kitchen.jsonschema;

import eel.kitchen.jsonschema.validators.Validator;
import eel.kitchen.jsonschema.validators.type.ObjectValidator;
import eel.kitchen.util.JasonHelper;
import org.codehaus.jackson.JsonNode;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertFalse;
import static org.testng.AssertJUnit.assertEquals;

public final class UnsupportedSchemasTest
{
    private static final JsonNode testNode;

    private SchemaNode schemaNode;
    private List<String> ret;

    static {
        try {
            testNode = JasonHelper.load("unsupported.json");
        } catch (IOException e) {
            throw new ExceptionInInitializerError();
        }
    }

    @Test
    public void testUnionType()
    {
        schemaNode = new SchemaNode(testNode.get("uniontypes"));

        assertFalse(schemaNode.isValid());

        ret = schemaNode.getMessages();

        assertEquals(ret.size(), 1);
        assertEquals(ret.get(0), "Sorry, union types not implemented yet");
    }

    @Test
    public void testRef()
    {
        schemaNode = new SchemaNode(testNode.get("$ref"));

        assertFalse(schemaNode.isValid());

        ret = schemaNode.getMessages();

        assertEquals(ret.size(), 1);
        assertEquals(ret.get(0), "Sorry, $ref not implemented yet");
    }

    @Test
    public void testExtends()
    {
        schemaNode = new SchemaNode(testNode.get("extends"));

        assertFalse(schemaNode.isValid());

        ret = schemaNode.getMessages();

        assertEquals(ret.size(), 1);
        assertEquals(ret.get(0), "Sorry, extends not implemented yet");
    }

    @Test
    public void testSchemaDep()
    {
        final Validator v = new ObjectValidator();

        v.setSchema(testNode.get("schemadep"));

        assertFalse(v.setup());

        ret = v.getMessages();

        assertEquals(ret.size(), 1);
        assertEquals(ret.get(0), "Sorry, schema dependencies "
            + "not implemented yet");
    }
}
